package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.DoctorAppointmentTime;
import com.polyclinic.mis.models.TherapistAppointmentTime;
import com.polyclinic.mis.service.DoctorAppointmentTimeService;
import com.polyclinic.mis.service.TherapistAppointmentTimeService;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
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
public class TherapistAppointmentTimeController {
    @Autowired
    TherapistAppointmentTimeService therapistAppointmentTimeService;

    @Autowired
    DoctorServiceImpl doctorService;

    @GetMapping("/TherapistAppointmentTimes/Index")
    public String Index(Model model){
//        Iterable<Assistant> assistants = assistantService.getAll();
//        model.addAttribute("assistants",assistants);
//        return "/Assistants/Index";
        return findPaginated(1, "time" , "asc","",model);
    }

    @GetMapping("/TherapistAppointmentTimes/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "doctorFIO") String doctorFio,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 20;


        Page<TherapistAppointmentTime> page = therapistAppointmentTimeService.findPaginated(pageNumber,pageSize,sortField,sortDir,doctorFio);
        List<TherapistAppointmentTime> therapistAppointmentTimes = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("therapistAppointmentTimes", therapistAppointmentTimes);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("doctorFIO",doctorFio);
        return "/TherapistAppointmentTimes/Index";
    }
    @GetMapping("/TherapistAppointmentTimes/Create")
    public String ShowCreateWithoutParam(Model model){
        TherapistAppointmentTime therapistAppointmentTime = new TherapistAppointmentTime();
        model.addAttribute("therapistAppointmentTime",therapistAppointmentTime);

        var doctors = doctorService.getAllNotTherapists();
        model.addAttribute("doctors",doctors);

        return "/TherapistAppointmentTimes/Create/"+therapistAppointmentTime.getDoctor().getId();
    }
    @GetMapping("/TherapistAppointmentTimes/Create/{doctorId}")
    public String ShowCreate(@PathVariable(required = false) Long doctorId,Model model){
        TherapistAppointmentTime therapistAppointmentTime = new TherapistAppointmentTime();
        model.addAttribute("therapistAppointmentTime",therapistAppointmentTime);
        model.addAttribute("doctorId",doctorId);

        return "/TherapistAppointmentTimes/Create";
    }

    @PostMapping("/TherapistAppointmentTimes/Create")
    public String Create(@Valid @ModelAttribute("therapistAppointmentTime") TherapistAppointmentTime therapistAppointmentTime,
                         BindingResult result,
                         Model model){
        if (result.hasErrors()){
            model.addAttribute("therapistAppointmentTime",therapistAppointmentTime);
            if (therapistAppointmentTime.getDoctor().equals(null)){
                var doctors = doctorService.getAllNotTherapists();
                model.addAttribute("doctors",doctors);
                return "/TherapistAppointmentTimes/Create";
            }
            else {
                model.addAttribute("doctorId",therapistAppointmentTime.getDoctor().getId());
                return "/TherapistAppointmentTimes/Create/"+therapistAppointmentTime.getDoctor().getId();
            }
        }
        else {
            Time time = Time.valueOf(therapistAppointmentTime.getTimeString() + ":00");
            therapistAppointmentTime.setTime(time);
            therapistAppointmentTimeService.add(therapistAppointmentTime);
            return "redirect:/TherapistAppointmentTimes/Create/" + therapistAppointmentTime.getDoctor().getId();
        }
    }



    @GetMapping("TherapistAppointmentTimes/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<TherapistAppointmentTime> therapistAppointmentTime = therapistAppointmentTimeService.getById(id);
        if (therapistAppointmentTime.isPresent()){
            therapistAppointmentTime.get().setTimeString(therapistAppointmentTime.get().getTime().toString());
            model.addAttribute("therapistAppointmentTime",therapistAppointmentTime.get());
            return "/TherapistAppointmentTimes/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }

    @PostMapping("TherapistAppointmentTimes/Edit")
    public String Edit(@Valid @ModelAttribute("therapistAppointmentTime") TherapistAppointmentTime therapistAppointmentTime,
                       BindingResult result,
                       Model model){
        if (result.hasErrors()){
            therapistAppointmentTime.setTimeString(therapistAppointmentTime.getTime().toString());
            model.addAttribute("therapistAppointmentTime",therapistAppointmentTime);
            return "TherapistAppointmentTimes/Edit/"+therapistAppointmentTime.getId();
        }
        else {
            Time time = Time.valueOf(therapistAppointmentTime.getTimeString() + ":00");
            therapistAppointmentTime.setTime(time);
            therapistAppointmentTimeService.edit(therapistAppointmentTime);
            return "redirect:/TherapistAppointmentTimes/Index";
        }
    }
    @GetMapping("TherapistAppointmentTimes/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        therapistAppointmentTimeService.delete(id);
        return "redirect:/TherapistAppointmentTimes/Index";
    }
    @GetMapping("TherapistAppointmentTimes/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<TherapistAppointmentTime> therapistAppointmentTime = therapistAppointmentTimeService.getById(id);
        if (therapistAppointmentTime.isPresent()){
            model.addAttribute("therapistAppointmentTime",therapistAppointmentTime.get());
            return "/TherapistAppointmentTimes/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
