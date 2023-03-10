package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import com.polyclinic.mis.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PatientController {

    @Autowired
    PatientServiceImpl patientService;
    @GetMapping("/Patients")
    public String Index(Model model){
        Iterable<Patient> patients = patientService.getAll();
        model.addAttribute("patients",patients);
        return "/Patients/Index";
    }
}
