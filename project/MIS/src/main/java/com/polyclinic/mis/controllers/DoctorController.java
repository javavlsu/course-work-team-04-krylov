package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.DoctorAppointment;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.print.Doc;
import java.util.Optional;

@Controller
public class DoctorController {
    @Autowired
    DoctorServiceImpl doctorService;
    @GetMapping("/Doctors")
    public String Index(Model model){
        Iterable<Doctor> doctors = doctorService.getAll();
        model.addAttribute("doctors",doctors);
        return "/Doctors/Index";
    }

    @GetMapping("/Doctors/Create")
    public String ShowCreate(Model model){
        Doctor doctor = new Doctor();
        model.addAttribute("doctor",doctor);
        return "/Doctors/Create";
    }
    @PostMapping("/Doctors/Create")
    public String Create(@ModelAttribute("doctor")Doctor doctor){
        doctorService.add(doctor);
        return "redirect:/Doctors";
    }
    @GetMapping("Doctors/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Doctor> doctor = doctorService.getById(id);
        if (doctor.isPresent()){
            model.addAttribute("doctor",doctor.get());
            return "/Doctors/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Doctors/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        doctorService.delete(id);
        return "redirect:/Doctors";
    }
    @GetMapping("Doctors/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Doctor> doctor = doctorService.getById(id);
        if (doctor.isPresent()){
            model.addAttribute("doctor",doctor.get());
            return "/Doctors/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
