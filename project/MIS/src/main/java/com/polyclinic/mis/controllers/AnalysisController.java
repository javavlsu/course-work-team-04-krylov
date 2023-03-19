package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Diagnosis;
import com.polyclinic.mis.service.impl.AnalysisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AnalysisController {
    @Autowired
    AnalysisServiceImpl analysisService;
    @GetMapping("/Analyses")
    public String Index(Model model){
        Iterable<Analysis> analyses = analysisService.getAll();
            model.addAttribute("analyses",analyses);
        return "/Analyses/Index";
    }
    @GetMapping("/Analyses/Create")
    public String ShowCreate(Model model){
        Analysis analysis = new Analysis();
        model.addAttribute("analysis",analysis);
        return "/Analyses/Create";
    }
    @PostMapping("/Analyses/Create")
    public String Create(@ModelAttribute("analysis")Analysis analysis){
        analysisService.add(analysis);
        return "redirect:/Analyses";
    }
    @GetMapping("Analyses/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Analysis> analysis = analysisService.getById(id);
        if (analysis.isPresent()){
            model.addAttribute("analyses",analysis.get());
            return "/Analyses/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Analyses/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        analysisService.delete(id);
        return "redirect:/Analyses";
    }
    @GetMapping("Analyses/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Analysis> analysis = analysisService.getById(id);
        if (analysis.isPresent()){
            model.addAttribute("analysis",analysis.get());
            return "/Analyses/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
