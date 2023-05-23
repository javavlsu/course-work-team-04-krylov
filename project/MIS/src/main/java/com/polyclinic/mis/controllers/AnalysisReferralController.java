package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.AnalysisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AnalysisReferralController {
    @Autowired
    AnalysisReferralServiceImpl analysisReferralService;
    @GetMapping("/AnalysisReferrals/Index")
    public String Index(Model model){
//        Iterable<AnalysisReferral> analysisReferrals = analysisReferralService.getAll();
//        model.addAttribute("analysisReferrals",analysisReferrals);
//        return "/AnalysisReferrals/Index";
        return findPaginated(1, "dateOfTaking" , "desc","","",model);
    }

    @GetMapping("/AnalysisReferrals/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<AnalysisReferral> page = analysisReferralService.findPaginated(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
        List<AnalysisReferral> analysisReferrals = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("analysisReferrals", analysisReferrals);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);

        return "/AnalysisReferrals/Index";
    }

    @GetMapping("/PatientAnalysisReferrals/Index")
    public String PatientIndex(Model model){
//        Iterable<AnalysisReferral> analysisReferrals = analysisReferralService.getAll();
//        model.addAttribute("analysisReferrals",analysisReferrals);
//        return "/AnalysisReferrals/Index";
        return patientFindPaginated(1, "dateOfTaking" , "desc",model);
    }

    @GetMapping("/PatientAnalysisReferrals/Index/{pageNumber}")
    public String patientFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<AnalysisReferral> page = analysisReferralService.patientFindPaginated(pageNumber,pageSize,sortField,sortDir);
        List<AnalysisReferral> analysisReferrals = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("analysisReferrals", analysisReferrals);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");

        return "/PatientAnalysisReferrals/Index";
    }

    @GetMapping("/AnalysisReferrals/Create")
    public String ShowCreateWithoutParam(Model model){
        AnalysisReferral analysisReferral = new AnalysisReferral();
        model.addAttribute("analysisReferral",analysisReferral);
        return "/AnalysisReferrals/Create";
    }
    @GetMapping("/AnalysisReferrals/Create/{patientId}")
    public String ShowCreate(@PathVariable(required = false) Long patientId, Model model){
        AnalysisReferral analysisReferral = new AnalysisReferral();
        model.addAttribute("analysisReferral",analysisReferral);
        return "/AnalysisReferrals/Create";
    }
    @PostMapping("/AnalysisReferrals/Create")
    public String Create(@ModelAttribute("analysisReferral")AnalysisReferral analysisReferral){
        analysisReferralService.add(analysisReferral);
        return "redirect:/AnalysisReferrals/Index";
    }
    @GetMapping("AnalysisReferrals/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<AnalysisReferral> analysisReferral = analysisReferralService.getById(id);
        if (analysisReferral.isPresent()){
            model.addAttribute("analysisReferral",analysisReferral.get());
            return "/AnalysisReferrals/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("AnalysisReferrals/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        analysisReferralService.delete(id);
        return "redirect:/AnalysisReferrals/Index";
    }
    @GetMapping("AnalysisReferrals/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<AnalysisReferral> analysisReferral = analysisReferralService.getById(id);
        if (analysisReferral.isPresent()){
            model.addAttribute("analysisReferral",analysisReferral.get());
            return "/AnalysisReferrals/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
