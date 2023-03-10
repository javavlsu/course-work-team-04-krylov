package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.DoctorAppointment;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.DoctorAppointmentServiceImpl;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DoctorAppointmentController {
    @Autowired
    DoctorAppointmentServiceImpl doctorAppointmentService;
    @GetMapping("/DoctorAppointments")
    public String Index(Model model){
        Iterable<DoctorAppointment> doctorAppointments = doctorAppointmentService.getAll();
        model.addAttribute("doctorAppointments",doctorAppointments);
        return "/DoctorAppointments/Index";
    }
}
