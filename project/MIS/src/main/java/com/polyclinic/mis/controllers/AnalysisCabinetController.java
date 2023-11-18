package com.polyclinic.mis.controllers;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisCabinet;
import com.polyclinic.mis.service.AnalysisCabinetService;
import com.polyclinic.mis.service.impl.AnalysisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AnalysisCabinetController {
    @Autowired
    AnalysisCabinetService analysisCabinetService;

    @GetMapping("/AnalysisCabinets/Index")
    public String Index(Model model){
//        Iterable<Analysis> analyses = analysisService.getAll();
//        model.addAttribute("analyses",analyses);
//        return "/Analyses/Index";
        return findPaginated(1, "number" , "desc","",model);
    }

    @GetMapping("/AnalysisCabinets/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "name") String name,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<AnalysisCabinet> page = analysisCabinetService.findPaginated(pageNumber,pageSize,sortField,sortDir,name);
        List<AnalysisCabinet> analysisCabinets = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("analysisCabinets", analysisCabinets);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("name",name);

        return "/AnalysisCabinets/Index";
    }

    @GetMapping("/AnalysisCabinets/Create")
    public String ShowCreate(Model model){
        AnalysisCabinet analysisCabinet = new AnalysisCabinet();
        model.addAttribute("analysisCabinet",analysisCabinet);
        return "/AnalysisCabinets/Create";
    }

    @PostMapping("/AnalysisCabinets/Create")
    public String Create(@ModelAttribute("analysisCabinet")AnalysisCabinet analysisCabinet){
        analysisCabinetService.add(analysisCabinet);
        return "redirect:/AnalysisCabinets/Index";
    }
    @GetMapping("AnalysisCabinets/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<AnalysisCabinet> analysisCabinet = analysisCabinetService.getById(id);
        if (analysisCabinet.isPresent()){
            model.addAttribute("analysisCabinet",analysisCabinet.get());
            return "/AnalysisCabinets/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @PostMapping("AnalysisCabinets/Edit")
    public String Edit(@ModelAttribute("analysisCabinet")AnalysisCabinet analysisCabinet){
        analysisCabinetService.edit(analysisCabinet);
        return "redirect:/AnalysisCabinets/Index";
    }

    @GetMapping("AnalysisCabinets/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        analysisCabinetService.delete(id);
        return "redirect:/AnalysisCabinets/Index";
    }
    @GetMapping("AnalysisCabinets/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<AnalysisCabinet> analysisCabinet = analysisCabinetService.getById(id);
        if (analysisCabinet.isPresent()){
            model.addAttribute("analysisCabinet",analysisCabinet.get());
            return "/AnalysisCabinets/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
