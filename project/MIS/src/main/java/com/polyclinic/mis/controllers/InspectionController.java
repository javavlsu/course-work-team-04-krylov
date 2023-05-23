package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.*;
import com.polyclinic.mis.service.impl.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class InspectionController {

    @Autowired
    InspectionServiceImpl inspectionService;
    @Autowired
    PatientServiceImpl patientService;

    @Autowired
    DiagnosisServiceImpl diagnosisService;

    @Autowired
    DoctorServiceImpl doctorService;
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

    @GetMapping("/PatientInspections/Index/{pageNumber}")
    public String patientFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Inspection> page = inspectionService.patientFindPaginated(pageNumber,pageSize,sortField,sortDir);
        List<Inspection> inspections = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("inspections", inspections);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");

        return "/PatientInspections/Index";
    }

    @GetMapping("/PatientInspections/Index")
    public String PatientIndex(Model model){
//        Iterable<Inspection> inspections = inspectionService.getAll();
//        model.addAttribute("inspections",inspections);
//        return "/Inspections/Index";
        return patientFindPaginated(1, "date" , "desc",model);
    }


    @GetMapping("/Inspections/Create")
    public String ShowCreateWithoutParam(Model model){
        Inspection inspection = new Inspection();
        model.addAttribute("inspection",inspection);
        var patients = patientService.getAll();
        model.addAttribute("patients",patients);

        var diagnoses = diagnosisService.getAll();
        model.addAttribute("diagnoses",diagnoses);
//
//        LocalDateTime currentDate = LocalDateTime.now();
//        model.addAttribute("currentDate",currentDate);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("currentDate",dateTimeFormatter.format(now));

        Doctor doctor = doctorService.currentDoctor();
        model.addAttribute("doctorId",doctor.getId());
        return "/Inspections/Create";
    }
    @GetMapping("/Inspections/Create/{patientId}")
    public String ShowCreate(@PathVariable(required = false) Long patientId, Model model){
        Inspection inspection = new Inspection();
        model.addAttribute("inspection",inspection);
        model.addAttribute("patientId",patientId);
        var diagnoses = diagnosisService.getAll();
        model.addAttribute("diagnoses",diagnoses);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("currentDate",dateTimeFormatter.format(now));


        Doctor doctor = doctorService.currentDoctor();
        model.addAttribute("doctorId",doctor.getId());

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
