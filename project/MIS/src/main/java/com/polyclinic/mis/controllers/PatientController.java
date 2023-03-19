package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import com.polyclinic.mis.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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
    @GetMapping("/Patients/Create")
    public String ShowCreate(Model model){
        Patient patient = new Patient();
        model.addAttribute("patient",patient);
        return "/Patients/Create";
    }
    @PostMapping("/Patients/Create")
    public String Create(@ModelAttribute("patient")Patient patient){
        patientService.add(patient);
        return "redirect:/Patients";
    }
    @GetMapping("Patients/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Patient> patient = patientService.getById(id);
        if (patient.isPresent()){
            model.addAttribute("patient",patient.get());
            return "/Patients/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Patients/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        patientService.delete(id);
        return "redirect:/Patients";
    }
    @GetMapping("Patients/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Patient> patient = patientService.getById(id);
        if (patient.isPresent()){
            model.addAttribute("patient",patient.get());
            return "/Patients/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
