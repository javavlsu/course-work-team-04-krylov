package com.polyclinic.mis.controllers;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.*;
import com.polyclinic.mis.repository.RoleRepository;
import com.polyclinic.mis.service.impl.ExaminationCabinetServiceImpl;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FunctionalDiagnosticsDoctorController {

    @Autowired
    FunctionalDiagnosticsDoctorServiceImpl functionalDiagnosticsDoctorService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ExaminationCabinetServiceImpl examinationCabinetService;
    @GetMapping("/FunctionalDiagnosticsDoctors/Index")
    public String Index(Model model){
//        Iterable<FunctionalDiagnosticsDoctor> functionalDiagnosticsDoctors = functionalDiagnosticsDoctorService.getAll();
//        model.addAttribute("functionalDiagnosticsDoctors",functionalDiagnosticsDoctors);
//        return "/FunctionalDiagnosticsDoctors/Index";
        return findPaginated(1, "lastName" , "desc","","",model);

    }

    @GetMapping("/FunctionalDiagnosticsDoctors/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "functionalDiagnosticsDoctorFIO") String functionalDiagnosticsDoctorFio,
            @RequestParam(value = "functionalDiagnosticsDoctorBirthDate") String functionalDiagnosticsDoctorBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<FunctionalDiagnosticsDoctor> page = functionalDiagnosticsDoctorService.findPaginated(pageNumber,pageSize,sortField,sortDir,functionalDiagnosticsDoctorFio,functionalDiagnosticsDoctorBirthDate);
        List<FunctionalDiagnosticsDoctor> functionalDiagnosticsDoctors = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("functionalDiagnosticsDoctors", functionalDiagnosticsDoctors);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("functionalDiagnosticsDoctorFIO",functionalDiagnosticsDoctorFio);
        model.addAttribute("functionalDiagnosticsDoctorBirthDate",functionalDiagnosticsDoctorBirthDate);
        return "/FunctionalDiagnosticsDoctors/Index";
    }
    @GetMapping("/FunctionalDiagnosticsDoctors/Create")
    public String ShowCreate(Model model){
        FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = new FunctionalDiagnosticsDoctor();
        model.addAttribute("functionalDiagnosticsDoctor",functionalDiagnosticsDoctor);
        return "/FunctionalDiagnosticsDoctors/Create";
    }
    @PostMapping("/FunctionalDiagnosticsDoctors/Create")
    public String Create(@ModelAttribute("examination")FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userService.findUserByEmail(email);
        functionalDiagnosticsDoctor.setUser(user);

        functionalDiagnosticsDoctorService.add(functionalDiagnosticsDoctor);

        assignRole(functionalDiagnosticsDoctor,user);
        return "redirect:/";
    }
    private void assignRole(FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor, PolyclinicUser user){
        var functionalDiagnosticsDoctorUser= functionalDiagnosticsDoctor.getUser();
        var roles = user.getRoles();
        var canRegisterAsFunctionalDiagnosticsDoctor = roleRepository.findByName("CanRegisterAsFunctionalDiagnosticsDoctor").get();
        roles.remove(canRegisterAsFunctionalDiagnosticsDoctor);
        var functionalDiagnosticsDoctorRole = roleRepository.findByName("FunctionalDiagnosticsDoctor").get();
        roles.add(functionalDiagnosticsDoctorRole);
        functionalDiagnosticsDoctorUser.setRoles(roles);
        userService.updateUser(functionalDiagnosticsDoctorUser);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        updatedAuthorities.remove(new SimpleGrantedAuthority(canRegisterAsFunctionalDiagnosticsDoctor.getName()));
        updatedAuthorities.add(new SimpleGrantedAuthority(functionalDiagnosticsDoctorRole.getName()));

        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    @GetMapping("FunctionalDiagnosticsDoctors/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<FunctionalDiagnosticsDoctor> functionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.getById(id);
        if (functionalDiagnosticsDoctor.isPresent()){
            model.addAttribute("functionalDiagnosticsDoctor",functionalDiagnosticsDoctor.get());
            var cabinets = examinationCabinetService.getAll();
            model.addAttribute("cabinets",cabinets);

            return "/FunctionalDiagnosticsDoctors/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }

    @PostMapping("FunctionalDiagnosticsDoctors/Edit")
    public String Edit(@ModelAttribute("functionalDiagnosticsDoctor") FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor){
        functionalDiagnosticsDoctorService.edit(functionalDiagnosticsDoctor);
        return "redirect:/FunctionalDiagnosticsDoctors/Index";
    }

    @GetMapping("FunctionalDiagnosticsDoctors/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        functionalDiagnosticsDoctorService.delete(id);
        return "redirect:/FunctionalDiagnosticsDoctors";
    }
    @GetMapping("FunctionalDiagnosticsDoctors/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<FunctionalDiagnosticsDoctor> functionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.getById(id);
        if (functionalDiagnosticsDoctor.isPresent()){
            model.addAttribute("functionalDiagnosticsDoctor",functionalDiagnosticsDoctor.get());
            return "/FunctionalDiagnosticsDoctors/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
