package com.polyclinic.mis.controllers.rest;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public String testEndpoint(){
        return "test";
    }
    @GetMapping("/doctors")
    public List<Doctor> doctorsGetAll(){
//
//        List<Doctor> doctorList = doctorService.getAll();
        return doctorService.getAll();


    }
    @GetMapping("/doctors/{id}")
    public Doctor doctorGetById(@PathVariable long id){
        return doctorService.getById(id).get();
    }
//    @PostMapping("/user/register")
//    public ResponseEntity register(@RequestParam(value = "email", required = true)String email,
//                                   @RequestParam(value = "password", required = true)String password){
//
//    }
}
