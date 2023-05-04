package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.Diagnosis;
import com.polyclinic.mis.service.impl.DiagnosisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Optional;

@Controller
public class DiagnosisController {
    @Autowired
    private DiagnosisServiceImpl diagnosisService;
    @GetMapping("/Diagnoses/Index")
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
        return "redirect:/Diagnoses/Index";
    }
    @GetMapping("Diagnoses/Edit/{id}")
    public String ShowEdit(@PathVariable (value = "id") String id, Model model){
        Optional<Diagnosis> diagnosis = diagnosisService.getById(id);
        if (diagnosis.isPresent()){
            model.addAttribute("diagnosis",diagnosis.get());
            return "/Diagnoses/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Diagnoses/Delete/{id}")
    public String Delete(@PathVariable (value = "id") String id, Model model){
        diagnosisService.delete(id);
        return "redirect:/Diagnoses/Index";
    }
    @GetMapping("Diagnoses/Details/{id}")
    public String Details(@PathVariable (value = "id") String id, Model model){
        Optional<Diagnosis> diagnosis = diagnosisService.getById(id);
        if (diagnosis.isPresent()){
            model.addAttribute("diagnosis",diagnosis.get());
            return "/Diagnoses/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
