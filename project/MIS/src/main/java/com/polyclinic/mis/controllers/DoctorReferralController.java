package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.DoctorReferral;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.DoctorReferralServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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

    @GetMapping("/DoctorReferrals/Create")
    public String ShowCreate(Model model){
        DoctorReferral doctorReferral = new DoctorReferral();
        model.addAttribute("doctorReferral",doctorReferral);
        return "/DoctorReferrals/Create";
    }
    @PostMapping("/DoctorReferrals/Create")
    public String Create(@ModelAttribute("doctorReferral")DoctorReferral doctorReferral){
        doctorReferralService.add(doctorReferral);
        return "redirect:/DoctorReferrals";
    }
    @GetMapping("DoctorReferrals/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<DoctorReferral> doctorReferral = doctorReferralService.getById(id);
        if (doctorReferral.isPresent()){
            model.addAttribute("doctorReferral",doctorReferral.get());
            return "/DoctorReferrals/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("DoctorReferrals/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        doctorReferralService.delete(id);
        return "redirect:/DoctorReferrals";
    }
    @GetMapping("DoctorReferrals/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<DoctorReferral> doctorReferral = doctorReferralService.getById(id);
        if (doctorReferral.isPresent()){
            model.addAttribute("doctorReferral",doctorReferral.get());
            return "/DoctorReferrals/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
