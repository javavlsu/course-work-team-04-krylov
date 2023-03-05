package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.Diagnosis;
import com.polyclinic.mis.service.DiagnosisService;
import com.polyclinic.mis.service.impl.DiagnosisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DiagnosisController {
    @Autowired
    private DiagnosisServiceImpl diagnosisService;
    @GetMapping("/Diagnoses")
    public String Index(Model model){
        Iterable<Diagnosis> diagnoses = diagnosisService.getAll();
        model.addAttribute("diagnoses",diagnoses);
        return "/Diagnoses/Index";
    }
}
