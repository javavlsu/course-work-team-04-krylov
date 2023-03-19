package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.models.Receptionist;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import com.polyclinic.mis.service.impl.ReceptionistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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
    @GetMapping("/Receptionists/Create")
    public String ShowCreate(Model model){
        Receptionist receptionist = new Receptionist();
        model.addAttribute("receptionist",receptionist);
        return "/Receptionists/Create";
    }
    @PostMapping("/Receptionists/Create")
    public String Create(@ModelAttribute("receptionist")Receptionist receptionist){
        receptionistService.add(receptionist);
        return "redirect:/Receptionists";
    }
    @GetMapping("Receptionists/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Receptionist> receptionist = receptionistService.getById(id);
        if (receptionist.isPresent()){
            model.addAttribute("receptionist",receptionist.get());
            return "/Receptionists/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Receptionists/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        receptionistService.delete(id);
        return "redirect:/Receptionists";
    }
    @GetMapping("Receptionists/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Receptionist> receptionist = receptionistService.getById(id);
        if (receptionist.isPresent()){
            model.addAttribute("patient",receptionist.get());
            return "/Receptionists/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
