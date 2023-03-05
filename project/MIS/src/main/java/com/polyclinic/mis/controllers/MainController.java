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
    @GetMapping("/Patients")
    public String patients(Model model){
        return "/Patients/Index";
    }
    @GetMapping("/TempPatientDetails")
    public String tempPatientDetails(Model model){
        return "/Patients/TempDetails";
    }
    @GetMapping("/Analyses")
    public String analyses(Model model) {
        return "/Analyses/Index";
    }
    @GetMapping("/Analyses/Create")
    public String analysisCreate(Model model) {
        return "/Analyses/Create";
    }
    @GetMapping("/AnalysisReferrals")
    public String analysisReferrals(Model model) {
        return "/AnalysisReferrals/Index";
    }
    @GetMapping("/Examinations")
    public String examinations(Model model) {
        return "/Examinations/Index";
    }
    @GetMapping("/Examinations/Create")
    public String examinationsCreate(Model model) {
        return "/Examinations/Create";
    }
    @GetMapping("/ExaminationReferrals")
    public String examinationReferrals(Model model) {
        return "/ExaminationReferrals/Index";
    }
//    @GetMapping("/Diagnoses")
//    public String diagnoses(Model model) {
//        return "/Diagnoses/Index";
//    }



    @GetMapping("/test")
    public String test(Model model){
        return "/Test";
    }

}
