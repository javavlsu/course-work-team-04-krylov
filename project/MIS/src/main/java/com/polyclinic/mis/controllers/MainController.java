package com.polyclinic.mis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Model model){
        return "/home";
    }

    /*
    @GetMapping("/Patients")
    public String patients(Model model){
        return "/Patients/Index";
    }

     */
    /*
    @GetMapping("/Analyses")
    public String analyses(Model model) {
        return "/Analyses/Index";
    }
     */


    @GetMapping("/test")
    public String test(Model model){
        return "/Test";
    }

}
