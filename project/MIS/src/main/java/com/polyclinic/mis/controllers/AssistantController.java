package com.polyclinic.mis.controllers;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.PolyclinicUser;
import com.polyclinic.mis.repository.RoleRepository;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.AssistantServiceImpl;
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
public class AssistantController {
    @Autowired
    AssistantServiceImpl assistantService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @GetMapping("/Assistants")
    public String Index(Model model){
        Iterable<Assistant> assistants = assistantService.getAll();
        model.addAttribute("assistants",assistants);
        return "/Assistants/Index";
    }

    @GetMapping("/Assistants/Create")
    public String ShowCreate(Model model){
        Assistant assistant = new Assistant();
        model.addAttribute("assistant",assistant);
        return "/Assistants/Create";
    }
    @PostMapping("/Assistants/Create")
    public String Create(@ModelAttribute("assistant")Assistant assistant){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userService.findUserByEmail(email);
        assistant.setUser(user);

        assistantService.add(assistant);

        assignRole(assistant,user);
        return "redirect:/Assistants";
    }
    private void assignRole(Assistant assistant, PolyclinicUser user){
        var assistantUser= assistant.getUser();
        var roles = user.getRoles();
        var canRegisterAsAssistant = roleRepository.findByName("CanRegisterAsAssistant").get();
        roles.remove(canRegisterAsAssistant);
        var assistantRole = roleRepository.findByName("Assistant").get();
        roles.add(assistantRole);
        assistantUser.setRoles(roles);
        userService.updateUser(assistantUser);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        updatedAuthorities.remove(new SimpleGrantedAuthority(canRegisterAsAssistant.getName()));
        updatedAuthorities.add(new SimpleGrantedAuthority(assistantRole.getName()));

        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }



    @GetMapping("Assistants/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Assistant> assistant = assistantService.getById(id);
        if (assistant.isPresent()){
            model.addAttribute("assistant",assistant.get());
            return "/Assistants/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Assistants/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        assistantService.delete(id);
        return "redirect:/Assistants";
    }
    @GetMapping("Assistants/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Assistant> assistant = assistantService.getById(id);
        if (assistant.isPresent()){
            model.addAttribute("assistant",assistant.get());
            return "/Assistants/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
