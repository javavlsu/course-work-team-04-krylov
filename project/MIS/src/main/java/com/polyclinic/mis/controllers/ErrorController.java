package com.polyclinic.mis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/Error")
    public String Index(Model model){
//        Iterable<DoctorReferral> doctorReferrals = doctorReferralService.getAll();
//        model.addAttribute("doctorReferrals",doctorReferrals);
//        return "/DoctorReferrals/Index";
        return "/Error";
    }
}
