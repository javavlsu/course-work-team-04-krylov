package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.ExaminationReferral;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.ExaminationReferralServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ExaminationReferralController {
    @Autowired
    ExaminationReferralServiceImpl examinationReferralService;
    @GetMapping("/ExaminationReferrals")
    public String Index(Model model){
        Iterable<ExaminationReferral> examinationReferrals= examinationReferralService.getAll();
        model.addAttribute("examinationReferrals",examinationReferrals);
        return "/ExaminationReferrals/Index";
    }

    @GetMapping("/ExaminationReferrals/Create")
    public String ShowCreate(Model model){
        ExaminationReferral examinationReferral = new ExaminationReferral();
        model.addAttribute("examinationReferral",examinationReferral);
        return "/ExaminationReferrals/Create";
    }
    @PostMapping("/ExaminationReferrals/Create")
    public String Create(@ModelAttribute("examinationReferral")ExaminationReferral examinationReferral){
        examinationReferralService.add(examinationReferral);
        return "redirect:/ExaminationReferrals";
    }
    @GetMapping("ExaminationReferrals/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<ExaminationReferral> examinationReferral = examinationReferralService.getById(id);
        if (examinationReferral.isPresent()){
            model.addAttribute("examinationReferral",examinationReferral.get());
            return "/ExaminationReferrals/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("ExaminationReferrals/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        examinationReferralService.delete(id);
        return "redirect:/ExaminationReferrals";
    }
    @GetMapping("ExaminationReferrals/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<ExaminationReferral> examinationReferral = examinationReferralService.getById(id);
        if (examinationReferral.isPresent()){
            model.addAttribute("examinationReferral",examinationReferral.get());
            return "/ExaminationReferrals/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
