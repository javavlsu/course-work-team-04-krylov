package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.print.Doc;
@Controller
public class DoctorController {
    @Autowired
    DoctorServiceImpl doctorService;
    @GetMapping("/Doctors")
    public String Index(Model model){
        Iterable<Doctor> doctors = doctorService.getAll();
        model.addAttribute("doctorAppointments",doctors);
        return "/Doctors/Index";
    }
}
