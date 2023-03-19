package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.DoctorReferral;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.ExaminationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ExaminationController {
    @Autowired
    ExaminationServiceImpl examinationService;
    @GetMapping("/Examinations")
    public String Index(Model model){
        Iterable<Examination> examinations = examinationService.getAll();
        model.addAttribute("examinations",examinations);
        return "/Examinations/Index";
    }
    @GetMapping("/Examinations/Create")
    public String ShowCreate(Model model){
        Examination examination = new Examination();
        model.addAttribute("examination",examination);
        return "/Examinations/Create";
    }
    @PostMapping("/Examinations/Create")
    public String Create(@ModelAttribute("examination")Examination examination){
        examinationService.add(examination);
        return "redirect:/Examinations";
    }
    @GetMapping("Examinations/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Examination> examination = examinationService.getById(id);
        if (examination.isPresent()){
            model.addAttribute("examination",examination.get());
            return "/Examinations/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Examinations/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        examinationService.delete(id);
        return "redirect:/Examinations";
    }
    @GetMapping("Examinations/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Examination> examination = examinationService.getById(id);
        if (examination.isPresent()){
            model.addAttribute("examination",examination.get());
            return "/Examinations/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }

}
