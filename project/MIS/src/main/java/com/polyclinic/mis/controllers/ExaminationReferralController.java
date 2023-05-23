package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.ExaminationReferral;
import com.polyclinic.mis.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ExaminationReferralController {
    @Autowired
    ExaminationReferralServiceImpl examinationReferralService;

    @Autowired
    PatientServiceImpl patientService;

    @Autowired
    DiagnosisServiceImpl diagnosisService;

    @Autowired
    DoctorServiceImpl doctorService;
    @GetMapping("/ExaminationReferrals/Index")
    public String Index(Model model){
        return findPaginated(1, "dateOfTaking" , "desc","","",model);
    }

    @GetMapping("/ExaminationReferrals/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<ExaminationReferral> page = examinationReferralService.findPaginated(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
        List<ExaminationReferral> examinationReferrals = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("examinationReferrals", examinationReferrals);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);

        return "/ExaminationReferrals/Index";
    }


    @GetMapping("/PatientExaminationReferrals/Index")
    public String PatientIndex(Model model){
        return patientFindPaginated(1, "dateOfTaking" , "desc",model);
    }

    @GetMapping("/PatientExaminationReferrals/Index/{pageNumber}")
    public String patientFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<ExaminationReferral> page = examinationReferralService.patientFindPaginated(pageNumber,pageSize,sortField,sortDir);
        List<ExaminationReferral> examinationReferrals = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("examinationReferrals", examinationReferrals);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");

        return "/PatientExaminationReferrals/Index";
    }

    @GetMapping("/ExaminationReferrals/Create")
    public String ShowCreateWithoutParam(Model model){
        ExaminationReferral examinationReferral = new ExaminationReferral();
        model.addAttribute("examinationReferral",examinationReferral);

        var patients = patientService.getAll();
        model.addAttribute("patients",patients);
        var diagnoses = diagnosisService.getAll();
        model.addAttribute("diagnoses",diagnoses);

        Doctor doctor = doctorService.currentDoctor();
        model.addAttribute("doctorId",doctor.getId());
        model.addAttribute("currentStatus","Выписано");


        return "/ExaminationReferrals/Create";
    }

    @GetMapping("/ExaminationReferrals/Create/{patientId}")
    public String ShowCreate(@PathVariable(required = false) Long patientId,Model model){
        ExaminationReferral examinationReferral = new ExaminationReferral();
        model.addAttribute("examinationReferral",examinationReferral);

        var diagnoses = diagnosisService.getAll();
        model.addAttribute("diagnoses",diagnoses);

        Doctor doctor = doctorService.currentDoctor();
        model.addAttribute("doctorId",doctor.getId());
        model.addAttribute("currentStatus","Выписано");
        return "/ExaminationReferrals/Create";
    }

    @PostMapping("/ExaminationReferrals/Create")
    public String Create(@ModelAttribute("examinationReferral")ExaminationReferral examinationReferral){
        examinationReferralService.add(examinationReferral);
        return "redirect:/ExaminationReferrals/Index";
    }
    @GetMapping("ExaminationReferrals/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<ExaminationReferral> examinationReferral = examinationReferralService.getById(id);
        if (examinationReferral.isPresent()){
            model.addAttribute("examinationReferral",examinationReferral.get());
            return "/ExaminationReferrals/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("ExaminationReferrals/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        examinationReferralService.delete(id);
        return "redirect:/ExaminationReferrals/Index";
    }
    @GetMapping("ExaminationReferrals/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<ExaminationReferral> examinationReferral = examinationReferralService.getById(id);
        if (examinationReferral.isPresent()){
            model.addAttribute("examinationReferral",examinationReferral.get());
            return "/ExaminationReferrals/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
