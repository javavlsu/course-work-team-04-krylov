package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.ExaminationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
