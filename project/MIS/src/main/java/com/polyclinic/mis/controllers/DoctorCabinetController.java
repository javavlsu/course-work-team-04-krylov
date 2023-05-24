package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.AnalysisCabinet;
import com.polyclinic.mis.models.DoctorCabinet;
import com.polyclinic.mis.service.AnalysisCabinetService;
import com.polyclinic.mis.service.DoctorCabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DoctorCabinetController {
    @Autowired
    DoctorCabinetService doctorCabinetService;

    @GetMapping("/DoctorCabinets/Index")
    public String Index(Model model){
//        Iterable<Analysis> analyses = analysisService.getAll();
//        model.addAttribute("analyses",analyses);
//        return "/Analyses/Index";
        return findPaginated(1, "number" , "desc","",model);
    }

    @GetMapping("/DoctorCabinets/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "name") String name,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<DoctorCabinet> page = doctorCabinetService.findPaginated(pageNumber,pageSize,sortField,sortDir,name);
        List<DoctorCabinet> doctorCabinets = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("doctorCabinets", doctorCabinets);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("name",name);

        return "/DoctorCabinets/Index";
    }

    @GetMapping("/DoctorCabinets/Create")
    public String ShowCreate(Model model){
        DoctorCabinet doctorCabinet = new DoctorCabinet();
        model.addAttribute("doctorCabinet",doctorCabinet);
        return "/DoctorCabinets/Create";
    }

    @PostMapping("/DoctorCabinets/Create")
    public String Create(@ModelAttribute("doctorCabinet")DoctorCabinet doctorCabinet){
        doctorCabinetService.add(doctorCabinet);
        return "redirect:/DoctorCabinets/Index";
    }
    @GetMapping("DoctorCabinets/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<DoctorCabinet> doctorCabinet = doctorCabinetService.getById(id);
        if (doctorCabinet.isPresent()){
            model.addAttribute("doctorCabinet",doctorCabinet.get());
            return "/DoctorCabinets/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @PostMapping("DoctorCabinets/Edit")
    public String Edit(@ModelAttribute("doctorCabinet")DoctorCabinet doctorCabinet){
        doctorCabinetService.edit(doctorCabinet);
        return "redirect:/DoctorCabinets/Index";
    }

    @GetMapping("DoctorCabinets/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        doctorCabinetService.delete(id);
        return "redirect:/DoctorCabinets/Index";
    }
    @GetMapping("DoctorCabinets/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<DoctorCabinet> doctorCabinet = doctorCabinetService.getById(id);
        if (doctorCabinet.isPresent()){
            model.addAttribute("doctorCabinet",doctorCabinet.get());
            return "/DoctorCabinets/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
