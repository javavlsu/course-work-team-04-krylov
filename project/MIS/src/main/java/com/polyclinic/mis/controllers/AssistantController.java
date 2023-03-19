package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.AssistantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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

    @GetMapping("/Assistants/Create")
    public String ShowCreate(Model model){
        Assistant assistant = new Assistant();
        model.addAttribute("assistant",assistant);
        return "/Assistants/Create";
    }
    @PostMapping("/Assistants/Create")
    public String Create(@ModelAttribute("assistant")Assistant assistant){
        assistantService.add(assistant);
        return "redirect:/Assistants";
    }
    @GetMapping("Assistants/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Assistant> assistant = assistantService.getById(id);
        if (assistant.isPresent()){
            model.addAttribute("assistant",assistant.get());
            return "/Assistants/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Assistants/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        assistantService.delete(id);
        return "redirect:/Assistants";
    }
    @GetMapping("Assistants/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Assistant> assistant = assistantService.getById(id);
        if (assistant.isPresent()){
            model.addAttribute("assistant",assistant.get());
            return "/Assistants/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
