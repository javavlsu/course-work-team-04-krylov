package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.DoctorAppointment;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.DoctorAppointmentServiceImpl;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DoctorAppointmentController {
    @Autowired
    DoctorAppointmentServiceImpl doctorAppointmentService;
    @GetMapping("/DoctorAppointments/Index")
    public String Index(Model model){
//        Iterable<DoctorAppointment> doctorAppointments = doctorAppointmentService.getAll();
//        model.addAttribute("doctorAppointments",doctorAppointments);
//        return "/DoctorAppointments/Index";
        return findPaginated(1, "status" , "desc","","","",model);
    }

    @GetMapping("/DoctorAppointments/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            @RequestParam(value = "status") String status,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<DoctorAppointment> page = doctorAppointmentService.findPaginated(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate,status);
        List<DoctorAppointment> doctorAppointments = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("doctorAppointments", doctorAppointments);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);
        model.addAttribute("status",status);


        return "/DoctorAppointments/Index";
    }

    @GetMapping("/DoctorAppointments/Create")
    public String ShowCreate(Model model){
        DoctorAppointment doctorAppointment = new DoctorAppointment();
        model.addAttribute("doctorAppointment",doctorAppointment);
        return "/DoctorAppointments/Create";
    }
    @PostMapping("/DoctorAppointments/Create")
    public String Create(@ModelAttribute("doctorAppointment")DoctorAppointment doctorAppointment){
        doctorAppointmentService.add(doctorAppointment);
        return "redirect:/DoctorAppointments/Index";
    }
    @GetMapping("DoctorAppointments/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<DoctorAppointment> doctorAppointment = doctorAppointmentService.getById(id);
        if (doctorAppointment.isPresent()){
            model.addAttribute("doctorAppointment",doctorAppointment.get());
            return "/DoctorAppointments/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("DoctorAppointments/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        doctorAppointmentService.delete(id);
        return "redirect:/DoctorAppointments/Index";
    }
    @GetMapping("DoctorAppointments/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<DoctorAppointment> doctorAppointment = doctorAppointmentService.getById(id);
        if (doctorAppointment.isPresent()){
            model.addAttribute("doctorAppointment",doctorAppointment.get());
            return "/DoctorAppointments/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
