package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.AssistantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AssistantController {
    @Autowired
    AssistantServiceImpl assistantService;
    @GetMapping("/Assistants")
    public String Index(Model model){
        Iterable<Assistant> assistants = assistantService.getAll();
        model.addAttribute("assistants",assistants);
        return "/Assistants/Index";
    }
}
