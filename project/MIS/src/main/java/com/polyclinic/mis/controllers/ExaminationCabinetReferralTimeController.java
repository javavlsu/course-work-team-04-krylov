package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.ExaminationCabinetReferralTime;
import com.polyclinic.mis.models.TherapistAppointmentTime;
import com.polyclinic.mis.service.ExaminationCabinetReferralTimeService;
import com.polyclinic.mis.service.TherapistAppointmentTimeService;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
import com.polyclinic.mis.service.impl.ExaminationCabinetReferralTimeServiceImpl;
import com.polyclinic.mis.service.impl.ExaminationCabinetServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Controller
public class ExaminationCabinetReferralTimeController {
    @Autowired
    ExaminationCabinetReferralTimeServiceImpl examinationCabinetReferralTimeService;
//
//    @Autowired
//    DoctorServiceImpl doctorService;

    @Autowired
    ExaminationCabinetServiceImpl examinationCabinetService;

    @GetMapping("/ExaminationCabinetReferralTimes/Index")
    public String Index(Model model){
//        Iterable<Assistant> assistants = assistantService.getAll();
//        model.addAttribute("assistants",assistants);
//        return "/Assistants/Index";
        return findPaginated(1, "time" , "asc","",model);
    }

    @GetMapping("/ExaminationCabinetReferralTimes/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "name") String name,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 20;


        Page<ExaminationCabinetReferralTime> page = examinationCabinetReferralTimeService.findPaginated(pageNumber,pageSize,sortField,sortDir,name);
        List<ExaminationCabinetReferralTime> examinationCabinetReferralTimes = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("examinationCabinetReferralTimes", examinationCabinetReferralTimes);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("name",name);
        return "/ExaminationCabinetReferralTimes/Index";
    }
    @GetMapping("/ExaminationCabinetReferralTimes/Create")
    public String ShowCreateWithoutParam(Model model){
        ExaminationCabinetReferralTime examinationCabinetReferralTime = new ExaminationCabinetReferralTime();
        model.addAttribute("examinationCabinetReferralTime",examinationCabinetReferralTime);

        var examinationCabinets = examinationCabinetService.getAll();
        model.addAttribute("examinationCabinets",examinationCabinets);

        return "/ExaminationCabinetReferralTimes/Create/"+examinationCabinetReferralTime.getExaminationCabinet().getId();
    }
    @GetMapping("/ExaminationCabinetReferralTimes/Create/{cabinetId}")
    public String ShowCreate(@PathVariable(required = false) Long cabinetId,Model model){
        ExaminationCabinetReferralTime examinationCabinetReferralTime = new ExaminationCabinetReferralTime();
        model.addAttribute("examinationCabinetReferralTime",examinationCabinetReferralTime);
        model.addAttribute("cabinetId",cabinetId);

        return "/ExaminationCabinetReferralTimes/Create";
    }

    @PostMapping("/ExaminationCabinetReferralTimes/Create")
    public String Create(@Valid @ModelAttribute("examinationCabinetReferralTime") ExaminationCabinetReferralTime examinationCabinetReferralTime,
                         BindingResult result,
                         Model model){
        if (result.hasErrors()){
            model.addAttribute("examinationCabinetReferralTime",examinationCabinetReferralTime);
            if (examinationCabinetReferralTime.getExaminationCabinet().equals(null)){
                var examinationCabinets = examinationCabinetService.getAll();
                model.addAttribute("examinationCabinets",examinationCabinets);
                return "/ExaminationCabinetReferralTimes/Create";
            }
            else {
                model.addAttribute("cabinetId",examinationCabinetReferralTime.getExaminationCabinet().getId());
                return "/ExaminationCabinetReferralTimes/Create/"+examinationCabinetReferralTime.getExaminationCabinet().getId();
            }
        }
        else {
            Time time = Time.valueOf(examinationCabinetReferralTime.getTimeString() + ":00");
            examinationCabinetReferralTime.setTime(time);
            examinationCabinetReferralTimeService.add(examinationCabinetReferralTime);
            return "redirect:/ExaminationCabinetReferralTimes/Create/" + examinationCabinetReferralTime.getExaminationCabinet().getId();
        }
    }



    @GetMapping("ExaminationCabinetReferralTimes/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<ExaminationCabinetReferralTime> examinationCabinetReferralTime = examinationCabinetReferralTimeService.getById(id);
        if (examinationCabinetReferralTime.isPresent()){
            examinationCabinetReferralTime.get().setTimeString(examinationCabinetReferralTime.get().getTime().toString());
            model.addAttribute("examinationCabinetReferralTime",examinationCabinetReferralTime.get());
            return "/ExaminationCabinetReferralTimes/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }

    @PostMapping("ExaminationCabinetReferralTimes/Edit")
    public String Edit(@Valid @ModelAttribute("examinationCabinetReferralTime") ExaminationCabinetReferralTime examinationCabinetReferralTime,
                       BindingResult result,
                       Model model){
        if (result.hasErrors()){
            examinationCabinetReferralTime.setTimeString(examinationCabinetReferralTime.getTime().toString());
            model.addAttribute("examinationCabinetReferralTime",examinationCabinetReferralTime);
            return "ExaminationCabinetReferralTimes/Edit/"+examinationCabinetReferralTime.getId();
        }
        else {
            Time time = Time.valueOf(examinationCabinetReferralTime.getTimeString() + ":00");
            examinationCabinetReferralTime.setTime(time);
            examinationCabinetReferralTimeService.edit(examinationCabinetReferralTime);
            return "redirect:/ExaminationCabinetReferralTimes/Index";
        }
    }
    @GetMapping("ExaminationCabinetReferralTimes/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        examinationCabinetReferralTimeService.delete(id);
        return "redirect:/ExaminationCabinetReferralTimes/Index";
    }
    @GetMapping("ExaminationCabinetReferralTimes/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<ExaminationCabinetReferralTime> examinationCabinetReferralTime = examinationCabinetReferralTimeService.getById(id);
        if (examinationCabinetReferralTime.isPresent()){
            model.addAttribute("examinationCabinetReferralTime",examinationCabinetReferralTime.get());
            return "/ExaminationCabinetReferralTimes/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
