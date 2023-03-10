package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Receptionist;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import com.polyclinic.mis.service.impl.ReceptionistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ReceptionistController {

    @Autowired
    ReceptionistServiceImpl receptionistService;
    @GetMapping("/Receptionists")
    public String Index(Model model){
        Iterable<Receptionist> receptionists = receptionistService.getAll();
        model.addAttribute("receptionists",receptionists);
        return "/Receptionists/Index";
    }
}
