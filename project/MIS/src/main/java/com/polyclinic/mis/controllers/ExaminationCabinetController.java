package com.polyclinic.mis.controllers;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.AnalysisCabinet;
import com.polyclinic.mis.models.ExaminationCabinet;
import com.polyclinic.mis.service.AnalysisCabinetService;
import com.polyclinic.mis.service.ExaminationCabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ExaminationCabinetController {
    @Autowired
    ExaminationCabinetService examinationCabinetService;

    @GetMapping("/ExaminationCabinets/Index")
    public String Index(Model model){
//        Iterable<Analysis> analyses = analysisService.getAll();
//        model.addAttribute("analyses",analyses);
//        return "/Analyses/Index";
        return findPaginated(1, "number" , "desc","",model);
    }

    @GetMapping("/ExaminationCabinets/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "name") String name,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<ExaminationCabinet> page = examinationCabinetService.findPaginated(pageNumber,pageSize,sortField,sortDir,name);
        List<ExaminationCabinet> examinationCabinets = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("examinationCabinets", examinationCabinets);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("name",name);

        return "/ExaminationCabinets/Index";
    }

    @GetMapping("/ExaminationCabinets/Create")
    public String ShowCreate(Model model){
        ExaminationCabinet examinationCabinet = new ExaminationCabinet();
        model.addAttribute("examinationCabinet",examinationCabinet);
        return "/ExaminationCabinets/Create";
    }

    @PostMapping("/ExaminationCabinets/Create")
    public String Create(@ModelAttribute("examinationCabinet")ExaminationCabinet examinationCabinet){
        examinationCabinetService.add(examinationCabinet);
        return "redirect:/ExaminationCabinets/Index";
    }
    @GetMapping("ExaminationCabinets/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<ExaminationCabinet> examinationCabinet = examinationCabinetService.getById(id);
        if (examinationCabinet.isPresent()){
            model.addAttribute("examinationCabinet",examinationCabinet.get());
            return "/ExaminationCabinets/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @PostMapping("ExaminationCabinets/Edit")
    public String Edit(@ModelAttribute("examinationCabinet")ExaminationCabinet examinationCabinet){
        examinationCabinetService.edit(examinationCabinet);
        return "redirect:/ExaminationCabinets/Index";
    }

    @GetMapping("ExaminationCabinets/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        examinationCabinetService.delete(id);
        return "redirect:/ExaminationCabinets/Index";
    }
    @GetMapping("ExaminationCabinets/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<ExaminationCabinet> examinationCabinet = examinationCabinetService.getById(id);
        if (examinationCabinet.isPresent()){
            model.addAttribute("examinationCabinet",examinationCabinet.get());
            return "/ExaminationCabinets/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
