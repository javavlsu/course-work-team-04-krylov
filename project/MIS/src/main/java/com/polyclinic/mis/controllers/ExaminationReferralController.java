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
}
