package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.DoctorReferralAppointment;
import com.polyclinic.mis.service.impl.DoctorReferralAppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DoctorReferralAppointmentController {
    @Autowired
    DoctorReferralAppointmentServiceImpl doctorReferralAppointmentService;
    @GetMapping("/DoctorReferralAppointments/Index")
    public String Index(Model model){
//        Iterable<DoctorReferralAppointment> doctorAppointments = doctorAppointmentService.getAll();
//        model.addAttribute("doctorAppointments",doctorAppointments);
//        return "/DoctorAppointments/Index";
        return findPaginated(1, "status" , "desc","","","",model);
    }

    @GetMapping("/DoctorReferralAppointments/Index/{pageNumber}")
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


        Page<DoctorReferralAppointment> page = doctorReferralAppointmentService.findPaginated(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate,status);
        List<DoctorReferralAppointment> doctorReferralAppointments = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("doctorReferralAppointments", doctorReferralAppointments);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);
        model.addAttribute("status",status);


        return "/DoctorReferralAppointments/Index";
    }

    @GetMapping("/PatientDoctorReferralAppointments/Index")
    public String PatientIndex(Model model){
//        Iterable<DoctorReferralAppointment> doctorAppointments = doctorAppointmentService.getAll();
//        model.addAttribute("doctorAppointments",doctorAppointments);
//        return "/DoctorAppointments/Index";
        return patientFindPaginated(1, "status" , "desc","",model);
    }

    @GetMapping("/PatientDoctorReferralAppointments/Index/{pageNumber}")
    public String patientFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "status") String status,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<DoctorReferralAppointment> page = doctorReferralAppointmentService.patientFindPaginated(pageNumber,pageSize,sortField,sortDir,status);
        List<DoctorReferralAppointment> doctorReferralAppointments = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("doctorReferralAppointments", doctorReferralAppointments);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("status",status);


        return "/PatientDoctorReferralAppointments/Index";
    }

    @GetMapping("/DoctorReferralAppointments/Create")
    public String ShowCreate(Model model){
        DoctorReferralAppointment doctorReferralAppointment = new DoctorReferralAppointment();
        model.addAttribute("doctorReferralAppointment", doctorReferralAppointment);
        return "/DoctorReferralAppointments/Create";
    }
    @PostMapping("/DoctorReferralAppointments/Create")
    public String Create(@ModelAttribute("doctorReferralAppointment") DoctorReferralAppointment doctorReferralAppointment){

        doctorReferralAppointmentService.add(doctorReferralAppointment);
        return "redirect:/DoctorReferralAppointments/Index";
    }
    @GetMapping("DoctorReferralAppointments/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<DoctorReferralAppointment> doctorAppointment = doctorReferralAppointmentService.getById(id);
        if (doctorAppointment.isPresent()){
            model.addAttribute("doctorReferralAppointment",doctorAppointment.get());
            return "/DoctorReferralAppointments/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("DoctorReferralAppointments/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        doctorReferralAppointmentService.delete(id);
        return "redirect:/DoctorReferralAppointments/Index";
    }
    @GetMapping("DoctorReferralAppointments/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<DoctorReferralAppointment> doctorAppointment = doctorReferralAppointmentService.getById(id);
        if (doctorAppointment.isPresent()){
            model.addAttribute("doctorReferralAppointment",doctorAppointment.get());
            return "/DoctorReferralAppointments/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
