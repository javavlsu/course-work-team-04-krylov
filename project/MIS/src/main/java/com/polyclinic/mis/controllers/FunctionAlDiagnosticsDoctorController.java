package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.FunctionalDiagnosticsDoctor;
import com.polyclinic.mis.service.impl.ExaminationReferralServiceImpl;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class FunctionAlDiagnosticsDoctorController {

    @Autowired
    FunctionalDiagnosticsDoctorServiceImpl functionalDiagnosticsDoctorService;
    @GetMapping("/FunctionalDiagnosticsDoctors")
    public String Index(Model model){
        Iterable<FunctionalDiagnosticsDoctor> functionalDiagnosticsDoctors = functionalDiagnosticsDoctorService.getAll();
        model.addAttribute("functionalDiagnosticsDoctors",functionalDiagnosticsDoctors);
        return "/FunctionalDiagnosticsDoctors/Index";
    }
}
