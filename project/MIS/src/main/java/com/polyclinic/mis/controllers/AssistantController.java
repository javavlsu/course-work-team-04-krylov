package com.polyclinic.mis.controllers;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.*;
import com.polyclinic.mis.repository.RoleRepository;
import com.polyclinic.mis.service.AnalysisCabinetService;
import com.polyclinic.mis.service.impl.AnalysisCabinetServiceImpl;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.AssistantServiceImpl;
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
public class AssistantController {
    @Autowired
    AssistantServiceImpl assistantService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AnalysisCabinetServiceImpl analysisCabinetService;
    @GetMapping("/Assistants/Index")
    public String Index(Model model){
//        Iterable<Assistant> assistants = assistantService.getAll();
//        model.addAttribute("assistants",assistants);
//        return "/Assistants/Index";
        return findPaginated(1, "lastName" , "desc","","",model);
    }

    @GetMapping("/Assistants/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "assistantFIO") String assistantFio,
            @RequestParam(value = "assistantBirthDate") String assistantBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Assistant> page = assistantService.findPaginated(pageNumber,pageSize,sortField,sortDir,assistantFio,assistantBirthDate);
        List<Assistant> assistants = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("assistants", assistants);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("assistantFIO",assistantFio);
        model.addAttribute("assistantBirthDate",assistantBirthDate);
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
        return "redirect:/Assistants/Index";
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

            var cabinets = analysisCabinetService.getAll();
            model.addAttribute("cabinets",cabinets);
            return "/Assistants/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }

    @PostMapping("Assistants/Edit")
    public String Edit(@ModelAttribute("assistant") Assistant assistant){
        assistantService.edit(assistant);
        return "redirect:/Assistants/Index";
    }
    @GetMapping("Assistants/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        assistantService.delete(id);
        return "redirect:/Assistants/Index";
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
