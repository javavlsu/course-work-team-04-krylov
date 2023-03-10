package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.DoctorReferral;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.DoctorReferralServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DoctorReferralController {
    @Autowired
    DoctorReferralServiceImpl doctorReferralService;
    @GetMapping("/DoctorReferrals")
    public String Index(Model model){
        Iterable<DoctorReferral> doctorReferrals = doctorReferralService.getAll();
        model.addAttribute("doctorReferrals",doctorReferrals);
        return "/DoctorReferrals/Index";
    }
}
