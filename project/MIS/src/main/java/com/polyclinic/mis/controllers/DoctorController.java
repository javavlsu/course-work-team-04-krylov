package com.polyclinic.mis.controllers;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.*;
import com.polyclinic.mis.repository.RoleRepository;
import com.polyclinic.mis.service.DoctorCabinetService;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
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

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DoctorController {
    @Autowired
    DoctorServiceImpl doctorService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    DoctorCabinetService doctorCabinetService;
    @GetMapping("/Doctors/Index")
    public String Index(Model model){
//        Iterable<Doctor> doctors = doctorService.getAll();
//        model.addAttribute("doctors",doctors);
//        return "/Doctors/Index";
        return findPaginated(1, "lastName" , "desc","","",model);
    }

    @GetMapping("/Doctors/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "doctorFIO") String doctorFIO,
            @RequestParam(value = "doctorSpeciality") String doctorSpeciality,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Doctor> page = doctorService.findPaginated(pageNumber,pageSize,sortField,sortDir,doctorFIO,doctorSpeciality);
        List<Doctor> doctors = page.getContent();
        String[] specialities = doctorService.getAllSpecialities();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("doctors", doctors);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("doctorFIO",doctorFIO);
        model.addAttribute("doctorSpeciality",doctorSpeciality);

        model.addAttribute("specialities",specialities);



        return "/Doctors/Index";
    }
    @GetMapping("/Doctors/Create")
    public String ShowCreate(Model model){
        Doctor doctor = new Doctor();
        model.addAttribute("doctor",doctor);
        return "/Doctors/Create";
    }
    @PostMapping("/Doctors/Create")
    public String Create(@ModelAttribute("doctor")Doctor doctor){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userService.findUserByEmail(email);
        doctor.setUser(user.get());
        doctorService.add(doctor);
        assignRole(doctor,user.get());
        return "redirect:/";
    }

    private void assignRole(Doctor doctor, PolyclinicUser user){
        var doctorUser= doctor.getUser();
        var roles = user.getRoles();
        var canRegisterAsDoctor = roleRepository.findByName("CanRegisterAsDoctor").get();
        roles.remove(canRegisterAsDoctor);
        var doctorRole = roleRepository.findByName("Doctor").get();
        roles.add(doctorRole);
        doctorUser.setRoles(roles);
        userService.updateUser(doctorUser);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        updatedAuthorities.remove(new SimpleGrantedAuthority(canRegisterAsDoctor.getName()));
        updatedAuthorities.add(new SimpleGrantedAuthority(doctorRole.getName()));

        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    @GetMapping("Doctors/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Doctor> doctor = doctorService.getById(id);
        if (doctor.isPresent()){
            model.addAttribute("doctor",doctor.get());

            var cabinets = doctorCabinetService.getAll();
            model.addAttribute("cabinets",cabinets);
            return "/Doctors/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @PostMapping("Doctors/Edit")
    public String Edit(@ModelAttribute("doctor") Doctor doctor){
        doctorService.edit(doctor);
        return "redirect:/Doctors/Index";
    }
    @GetMapping("Doctors/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        doctorService.delete(id);
        return "redirect:/Doctors/Index";
    }
    @GetMapping("Doctors/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Doctor> doctor = doctorService.getById(id);
        if (doctor.isPresent()){
            model.addAttribute("doctor",doctor.get());
            return "/Doctors/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
