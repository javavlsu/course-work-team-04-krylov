package com.polyclinic.mis.controllers;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Diagnosis;
import com.polyclinic.mis.service.impl.AnalysisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class AnalysisController {
    @Autowired
    AnalysisServiceImpl analysisService;
    @Autowired
    UserService userService;
    @GetMapping("/Analyses/Index")
    public String Index(Model model){
//        Iterable<Analysis> analyses = analysisService.getAll();
//        model.addAttribute("analyses",analyses);
//        return "/Analyses/Index";
        return findPaginated(1, "date" , "desc","","",model);
    }

    @GetMapping("/Analyses/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Analysis> page = analysisService.findPaginated(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
        List<Analysis> analysisList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("analyses", analysisList);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);

        return "/Analyses/Index";
    }
    @GetMapping("/PatientAnalyses/Index")
    public String PatientIndex(Model model){
//        Iterable<Analysis> analyses = analysisService.getAll();
//        model.addAttribute("analyses",analyses);
//        return "/Analyses/Index";
        return PatientFindPaginated(1, "date" , "desc",model);
    }

    @GetMapping("/PatientAnalyses/Index/{pageNumber}")
    public String PatientFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Analysis> page = analysisService.patientFindPaginated(pageNumber,pageSize,sortField,sortDir);
        List<Analysis> analysisList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("analyses", analysisList);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");


        return "/PatientAnalyses/Index";
    }



    @GetMapping("/Analyses/Create")
    public String ShowCreateWithoutParam(Model model){
        Analysis analysis = new Analysis();
        model.addAttribute("analysis",analysis);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userService.findUserByEmail(authentication.getName());
        model.addAttribute("user",user);

        return "/Analyses/Create";
    }

    @GetMapping("/Analyses/Create/{patientId}")
    public String ShowCreate(Model model){
        Analysis analysis = new Analysis();
        model.addAttribute("analysis",analysis);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userService.findUserByEmail(authentication.getName());
        model.addAttribute("user",user);

        return "/Analyses/Create";
    }
    @PostMapping("/Analyses/Create")
    public String Create(@ModelAttribute("analysis")Analysis analysis){
        analysisService.add(analysis);
        return "redirect:/Analyses/Index";
    }
    @GetMapping("Analyses/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Analysis> analysis = analysisService.getById(id);
        if (analysis.isPresent()){
            model.addAttribute("analysis",analysis.get());
            return "/Analyses/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Analyses/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        analysisService.delete(id);
        return "redirect:/Analyses/Index";
    }
    @GetMapping("Analyses/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Analysis> analysis = analysisService.getById(id);
        if (analysis.isPresent()){
            model.addAttribute("analysis",analysis.get());
            return "/Analyses/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
