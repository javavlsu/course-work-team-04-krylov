package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.service.AnalysisService;
import com.polyclinic.mis.service.impl.AnalysisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
