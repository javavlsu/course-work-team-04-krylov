package com.polyclinic.mis.controllers;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.PolyclinicUser;
import com.polyclinic.mis.models.Role;
import com.polyclinic.mis.models.RoleAssigment;
import com.polyclinic.mis.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RoleRepository roleRepository;
    @GetMapping("AssignUserToARole/Create")
    public String ShowCreate(Model model){
        RoleAssigment roleAssigment = new RoleAssigment();
        List<PolyclinicUser> users = userService.getAllUsers();
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("users",users);
        model.addAttribute("roles",roles);
        model.addAttribute("roleAssigment",roleAssigment);
        return "AssignUserToARole/Create";
    }
    @PostMapping ("AssignUserToARole/Create")
    public String Create(@ModelAttribute("roleAssigment")RoleAssigment roleAssigment){
        var user = userService.getUserById(roleAssigment.getUserId());
        //todo Service for Role entity
        var assigmentRole = roleRepository.findById(roleAssigment.getRoleId()).get();
        var userRoles = user.getRoles();
        userRoles.add(assigmentRole);
        user.setRoles(userRoles);
        userService.updateUser(user);
        return "redirect:/AssignUserToARole/Create";
    }
}
