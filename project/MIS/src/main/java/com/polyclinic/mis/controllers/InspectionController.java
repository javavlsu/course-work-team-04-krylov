package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.Inspection;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import com.polyclinic.mis.service.impl.InspectionServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class InspectionController {

    @Autowired
    InspectionServiceImpl inspectionService;
    @GetMapping("/Inspections")
    public String Index(Model model){
        Iterable<Inspection> inspections = inspectionService.getAll();
        model.addAttribute("inspections",inspections);
        return "/Inspections/Index";
    }

    @GetMapping("/Inspections/Create")
    public String ShowCreate(Model model){
        Inspection inspection = new Inspection();
        model.addAttribute("inspection",inspection);
        return "/Inspections/Create";
    }
    @PostMapping("/Inspections/Create")
    public String Create(@ModelAttribute("inspection")Inspection inspection){
        inspectionService.add(inspection);
        return "redirect:/Inspections";
    }
    @GetMapping("Inspections/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Inspection> inspection = inspectionService.getById(id);
        if (inspection.isPresent()){
            model.addAttribute("inspection",inspection.get());
            return "/Inspections/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Inspections/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        inspectionService.delete(id);
        return "redirect:/Inspections";
    }
    @GetMapping("Inspections/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Inspection> inspection = inspectionService.getById(id);
        if (inspection.isPresent()){
            model.addAttribute("inspection",inspection.get());
            return "/Inspections/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
