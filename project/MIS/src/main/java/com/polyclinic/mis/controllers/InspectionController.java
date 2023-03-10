package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Inspection;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import com.polyclinic.mis.service.impl.InspectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class InspectionController {

    @Autowired
    InspectionServiceImpl inspectionService;
    @GetMapping("/Inspections")
    public String Index(Model model){
        Iterable<Inspection> inspections = inspectionService.getAll();
        model.addAttribute("inspections",inspections);
        return "/Inspections/Index";
    }
}
