package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.DoctorAppointment;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.DoctorAppointmentServiceImpl;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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

    @GetMapping("/DoctorAppointments/Create")
    public String ShowCreate(Model model){
        DoctorAppointment doctorAppointment = new DoctorAppointment();
        model.addAttribute("doctorAppointment",doctorAppointment);
        return "/DoctorAppointments/Create";
    }
    @PostMapping("/DoctorAppointments/Create")
    public String Create(@ModelAttribute("doctorAppointment")DoctorAppointment doctorAppointment){
        doctorAppointmentService.add(doctorAppointment);
        return "redirect:/DoctorAppointments";
    }
    @GetMapping("DoctorAppointments/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<DoctorAppointment> doctorAppointment = doctorAppointmentService.getById(id);
        if (doctorAppointment.isPresent()){
            model.addAttribute("doctorAppointment",doctorAppointment.get());
            return "/DoctorAppointments/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("DoctorAppointments/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        doctorAppointmentService.delete(id);
        return "redirect:/DoctorAppointments";
    }
    @GetMapping("DoctorAppointments/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<DoctorAppointment> doctorAppointment = doctorAppointmentService.getById(id);
        if (doctorAppointment.isPresent()){
            model.addAttribute("doctorAppointment",doctorAppointment.get());
            return "/DoctorAppointments/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
