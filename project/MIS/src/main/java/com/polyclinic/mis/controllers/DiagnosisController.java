package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.Diagnosis;
import com.polyclinic.mis.service.impl.DiagnosisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @GetMapping("/Diagnoses/Create")
    public String ShowCreate(Model model){
        Diagnosis diagnosis = new Diagnosis();
        model.addAttribute("diagnosis",diagnosis);
        return "/Diagnoses/Create";
    }
    @PostMapping("/Diagnoses/Create")
    public String Create(@ModelAttribute("diagnosis")Diagnosis diagnosis){
        diagnosisService.add(diagnosis);
        return "redirect:/Diagnoses";
    }
    @GetMapping("Diagnoses/Edit/{id}")
    public String ShowEdit(@PathVariable (value = "id") String id, Model model){
        Optional<Diagnosis> diagnosis = diagnosisService.getById(id);
        if (diagnosis.isPresent()){
            model.addAttribute("diagnosis",diagnosis);
            return "/Diagnoses/Update";
        }
        else {
            return "/Error";
        }
    }
}
