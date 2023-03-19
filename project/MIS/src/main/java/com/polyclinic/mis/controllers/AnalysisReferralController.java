package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
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
public class AnalysisReferralController {
    @Autowired
    AnalysisReferralServiceImpl analysisReferralService;
    @GetMapping("/AnalysisReferrals")
    public String Index(Model model){
        Iterable<AnalysisReferral> analysisReferrals = analysisReferralService.getAll();
        model.addAttribute("analysisReferrals",analysisReferrals);
        return "/AnalysisReferrals/Index";
    }

    @GetMapping("/AnalysisReferrals/Create")
    public String ShowCreate(Model model){
        AnalysisReferral analysisReferral = new AnalysisReferral();
        model.addAttribute("analysisReferral",analysisReferral);
        return "/AnalysisReferrals/Create";
    }
    @PostMapping("/AnalysisReferrals/Create")
    public String Create(@ModelAttribute("analysisReferral")AnalysisReferral analysisReferral){
        analysisReferralService.add(analysisReferral);
        return "redirect:/AnalysisReferrals";
    }
    @GetMapping("AnalysisReferrals/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<AnalysisReferral> analysisReferral = analysisReferralService.getById(id);
        if (analysisReferral.isPresent()){
            model.addAttribute("analysisReferral",analysisReferral.get());
            return "/AnalysisReferrals/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("AnalysisReferrals/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        analysisReferralService.delete(id);
        return "redirect:/AnalysisReferrals";
    }
    @GetMapping("AnalysisReferrals/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<AnalysisReferral> analysisReferral = analysisReferralService.getById(id);
        if (analysisReferral.isPresent()){
            model.addAttribute("analysisReferral",analysisReferral.get());
            return "/AnalysisReferrals/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
