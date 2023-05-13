package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.DoctorReferral;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.ExaminationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ExaminationController {
    @Autowired
    ExaminationServiceImpl examinationService;
    @GetMapping("/Examinations/Index")
    public String Index(Model model){
//        Iterable<Examination> examinations = examinationService.getAll();
//        model.addAttribute("examinations",examinations);
//        return "/Examinations/Index";
        return findPaginated(1, "date" , "desc","","",model);
    }

    @GetMapping("/Examinations/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Examination> page = examinationService.findPaginated(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
        List<Examination> examinations = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("examinations", examinations);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);

        return "/Examinations/Index";
    }

    @GetMapping("/PatientExaminations/Index")
    public String PatientIndex(Model model){
//        Iterable<Examination> examinations = examinationService.getAll();
//        model.addAttribute("examinations",examinations);
//        return "/Examinations/Index";
        return patientFindPaginated(1, "date" , "desc",model);
    }

    @GetMapping("/PatientExaminations/Index/{pageNumber}")
    public String patientFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Examination> page = examinationService.patientFindPaginated(pageNumber,pageSize,sortField,sortDir);
        List<Examination> examinations = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("examinations", examinations);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");

        return "/PatientExaminations/Index";
    }

    @GetMapping("/Examinations/Create")
    public String ShowCreate(Model model){
        Examination examination = new Examination();
        model.addAttribute("examination",examination);
        return "/Examinations/Create";
    }
    @PostMapping("/Examinations/Create")
    public String Create(@ModelAttribute("examination")Examination examination){
        examinationService.add(examination);
        return "redirect:/Examinations/Index";
    }
    @GetMapping("Examinations/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Examination> examination = examinationService.getById(id);
        if (examination.isPresent()){
            model.addAttribute("examination",examination.get());
            return "/Examinations/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Examinations/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        examinationService.delete(id);
        return "redirect:/Examinations/Index";
    }
    @GetMapping("Examinations/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Examination> examination = examinationService.getById(id);
        if (examination.isPresent()){
            model.addAttribute("examination",examination.get());
            return "/Examinations/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }

}
