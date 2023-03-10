package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.AnalysisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
