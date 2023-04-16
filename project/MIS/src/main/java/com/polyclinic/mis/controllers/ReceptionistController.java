package com.polyclinic.mis.controllers;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.models.PolyclinicUser;
import com.polyclinic.mis.models.Receptionist;
import com.polyclinic.mis.repository.RoleRepository;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import com.polyclinic.mis.service.impl.ReceptionistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ReceptionistController {

    @Autowired
    ReceptionistServiceImpl receptionistService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @GetMapping("/Receptionists")
    public String Index(Model model){
        Iterable<Receptionist> receptionists = receptionistService.getAll();
        model.addAttribute("receptionists",receptionists);
        return "/Receptionists/Index";
    }
    @GetMapping("/Receptionists/Create")
    public String ShowCreate(Model model){
        Receptionist receptionist = new Receptionist();
        model.addAttribute("receptionist",receptionist);
        return "/Receptionists/Create";
    }
    @PostMapping("/Receptionists/Create")
    public String Create(@ModelAttribute("receptionist")Receptionist receptionist){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userService.findUserByEmail(email);
        receptionist.setUser(user);

        receptionistService.add(receptionist);

        assignRole(receptionist,user);
        return "redirect:/Receptionists";
    }
    private void assignRole(Receptionist receptionist, PolyclinicUser user){
        var receptionistUser= receptionist.getUser();
        var roles = user.getRoles();
        var canRegisterAsReceptionist = roleRepository.findByName("CanRegisterAsReceptionist").get();
        roles.remove(canRegisterAsReceptionist);
        var receptionistRole = roleRepository.findByName("Receptionist").get();
        roles.add(receptionistRole);
        receptionistUser.setRoles(roles);
        userService.updateUser(receptionistUser);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        updatedAuthorities.remove(new SimpleGrantedAuthority(canRegisterAsReceptionist.getName()));
        updatedAuthorities.add(new SimpleGrantedAuthority(receptionistRole.getName()));

        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    @GetMapping("Receptionists/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Receptionist> receptionist = receptionistService.getById(id);
        if (receptionist.isPresent()){
            model.addAttribute("receptionist",receptionist.get());
            return "/Receptionists/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Receptionists/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        receptionistService.delete(id);
        return "redirect:/Receptionists";
    }
    @GetMapping("Receptionists/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Receptionist> receptionist = receptionistService.getById(id);
        if (receptionist.isPresent()){
            model.addAttribute("patient",receptionist.get());
            return "/Receptionists/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
