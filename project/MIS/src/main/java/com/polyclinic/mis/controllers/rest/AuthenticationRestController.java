package com.polyclinic.mis.controllers.rest;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthenticationRestController {
    @Autowired
    UserService userService;
    @Autowired
    DoctorService doctorService;

    @GetMapping("/test")
//    @PreAuthorize("hasAnyRole('Assistant')")

    public String testEndpoint(){
        return "test";
    }
    @PostMapping("/checkAuthentication")
    public Boolean checkAuthentication(){
        return true;
    }

//    @PostMapping("/user/register")
//    public ResponseEntity register(@RequestParam(value = "email", required = true)String email,
//                                   @RequestParam(value = "password", required = true)String password){
//
//    }
}
