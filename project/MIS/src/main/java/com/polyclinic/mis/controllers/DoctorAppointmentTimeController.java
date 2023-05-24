package com.polyclinic.mis.controllers;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.DoctorAppointmentTime;
import com.polyclinic.mis.models.PolyclinicUser;
import com.polyclinic.mis.repository.DoctorRepository;
import com.polyclinic.mis.repository.RoleRepository;
import com.polyclinic.mis.service.DoctorAppointmentTimeService;
import com.polyclinic.mis.service.impl.AnalysisCabinetServiceImpl;
import com.polyclinic.mis.service.impl.AssistantServiceImpl;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DoctorAppointmentTimeController {
    @Autowired
    DoctorAppointmentTimeService doctorAppointmentTimeService;

    @Autowired
    DoctorServiceImpl doctorService;

    @GetMapping("/DoctorAppointmentTimes/Index")
    public String Index(Model model){
//        Iterable<Assistant> assistants = assistantService.getAll();
//        model.addAttribute("assistants",assistants);
//        return "/Assistants/Index";
        return findPaginated(1, "time" , "asc","",model);
    }

    @GetMapping("/DoctorAppointmentTimes/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "doctorFIO") String doctorFio,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<DoctorAppointmentTime> page = doctorAppointmentTimeService.findPaginated(pageNumber,pageSize,sortField,sortDir,doctorFio);
        List<DoctorAppointmentTime> doctorAppointmentTimes = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("doctorAppointmentTimes", doctorAppointmentTimes);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("doctorFIO",doctorFio);
        return "/DoctorAppointmentTimes/Index";
    }
    @GetMapping("/DoctorAppointmentTimes/Create")
    public String ShowCreateWithoutParam(Model model){
        DoctorAppointmentTime doctorAppointmentTime = new DoctorAppointmentTime();
        model.addAttribute("doctorAppointmentTime",doctorAppointmentTime);

        var doctors = doctorService.getAllNotTherapists();
        model.addAttribute("doctors",doctors);

        return "/DoctorAppointmentTimes/Create/"+doctorAppointmentTime.getDoctor().getId();
    }
    @GetMapping("/DoctorAppointmentTimes/Create/{doctorId}")
    public String ShowCreate(@PathVariable(required = false) Long doctorId,Model model){
        DoctorAppointmentTime doctorAppointmentTime = new DoctorAppointmentTime();
        model.addAttribute("doctorAppointmentTime",doctorAppointmentTime);
        model.addAttribute("doctorId",doctorId);

        return "/DoctorAppointmentTimes/Create";
    }

    @PostMapping("/DoctorAppointmentTimes/Create")
    public String Create(@ModelAttribute("doctorAppointmentTime") DoctorAppointmentTime doctorAppointmentTime){
        Time time = Time.valueOf(doctorAppointmentTime.getTimeString()+":00");
        doctorAppointmentTime.setTime(time);
        doctorAppointmentTimeService.add(doctorAppointmentTime);
        return "redirect:/DoctorAppointmentTimes/Create/"+doctorAppointmentTime.getDoctor().getId();
    }



    @GetMapping("DoctorAppointmentTimes/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<DoctorAppointmentTime> doctorAppointmentTime = doctorAppointmentTimeService.getById(id);
        if (doctorAppointmentTime.isPresent()){
            doctorAppointmentTime.get().setTimeString(doctorAppointmentTime.get().getTime().toString());
            model.addAttribute("doctorAppointmentTime",doctorAppointmentTime.get());
            return "/DoctorAppointmentTimes/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }

    @PostMapping("DoctorAppointmentTimes/Edit")
    public String Edit(@ModelAttribute("doctorAppointmentTime") DoctorAppointmentTime doctorAppointmentTime){
        Time time = Time.valueOf(doctorAppointmentTime.getTimeString()+":00");
        doctorAppointmentTime.setTime(time);
        doctorAppointmentTimeService.edit(doctorAppointmentTime);
        return "redirect:/DoctorAppointmentTimes/Index";
    }
    @GetMapping("DoctorAppointmentTimes/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        doctorAppointmentTimeService.delete(id);
        return "redirect:/DoctorAppointmentTimes/Index";
    }
    @GetMapping("DoctorAppointmentTimes/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<DoctorAppointmentTime> doctorAppointmentTime = doctorAppointmentTimeService.getById(id);
        if (doctorAppointmentTime.isPresent()){
            model.addAttribute("doctorAppointmentTime",doctorAppointmentTime.get());
            return "/DoctorAppointmentTimes/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
