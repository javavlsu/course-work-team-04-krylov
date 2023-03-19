package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.FunctionalDiagnosticsDoctor;
import com.polyclinic.mis.service.impl.ExaminationReferralServiceImpl;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class FunctionAlDiagnosticsDoctorController {

    @Autowired
    FunctionalDiagnosticsDoctorServiceImpl functionalDiagnosticsDoctorService;
    @GetMapping("/FunctionalDiagnosticsDoctors")
    public String Index(Model model){
        Iterable<FunctionalDiagnosticsDoctor> functionalDiagnosticsDoctors = functionalDiagnosticsDoctorService.getAll();
        model.addAttribute("functionalDiagnosticsDoctors",functionalDiagnosticsDoctors);
        return "/FunctionalDiagnosticsDoctors/Index";
    }

    @GetMapping("/FunctionalDiagnosticsDoctors/Create")
    public String ShowCreate(Model model){
        FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = new FunctionalDiagnosticsDoctor();
        model.addAttribute("functionalDiagnosticsDoctor",functionalDiagnosticsDoctor);
        return "/FunctionalDiagnosticsDoctors/Create";
    }
    @PostMapping("/FunctionalDiagnosticsDoctors/Create")
    public String Create(@ModelAttribute("examination")FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor){
        functionalDiagnosticsDoctorService.add(functionalDiagnosticsDoctor);
        return "redirect:/FunctionalDiagnosticsDoctors";
    }
    @GetMapping("FunctionalDiagnosticsDoctors/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<FunctionalDiagnosticsDoctor> functionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.getById(id);
        if (functionalDiagnosticsDoctor.isPresent()){
            model.addAttribute("functionalDiagnosticsDoctor",functionalDiagnosticsDoctor.get());
            return "/FunctionalDiagnosticsDoctors/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("FunctionalDiagnosticsDoctors/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        functionalDiagnosticsDoctorService.delete(id);
        return "redirect:/FunctionalDiagnosticsDoctors";
    }
    @GetMapping("FunctionalDiagnosticsDoctors/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<FunctionalDiagnosticsDoctor> functionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.getById(id);
        if (functionalDiagnosticsDoctor.isPresent()){
            model.addAttribute("functionalDiagnosticsDoctor",functionalDiagnosticsDoctor.get());
            return "/FunctionalDiagnosticsDoctors/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
