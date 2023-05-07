package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.Inspection;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import com.polyclinic.mis.service.impl.InspectionServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class InspectionController {

    @Autowired
    InspectionServiceImpl inspectionService;
    @GetMapping("/Inspections/Index")
    public String Index(Model model){
//        Iterable<Inspection> inspections = inspectionService.getAll();
//        model.addAttribute("inspections",inspections);
//        return "/Inspections/Index";
        return findPaginated(1, "date" , "desc","","",model);
    }


    @GetMapping("/Inspections/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Inspection> page = inspectionService.findPaginated(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
        List<Inspection> inspections = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("inspections", inspections);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);

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
        return "redirect:/Inspections/Index";
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
        return "redirect:/Inspections/Index";
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
