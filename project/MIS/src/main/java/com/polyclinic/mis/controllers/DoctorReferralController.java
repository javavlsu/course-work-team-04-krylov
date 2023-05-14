package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.DoctorReferral;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.DoctorReferralServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DoctorReferralController {
    @Autowired
    DoctorReferralServiceImpl doctorReferralService;
    @GetMapping("/DoctorReferrals/Index")
    public String Index(Model model){
//        Iterable<DoctorReferral> doctorReferrals = doctorReferralService.getAll();
//        model.addAttribute("doctorReferrals",doctorReferrals);
//        return "/DoctorReferrals/Index";
        return findPaginated(1, "dateOfTaking" , "desc","","",model);

    }

    @GetMapping("/DoctorReferrals/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<DoctorReferral> page = doctorReferralService.findPaginated(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
        List<DoctorReferral> doctorReferrals = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("doctorReferrals", doctorReferrals);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);

        return "/DoctorReferrals/Index";
    }


    @GetMapping("/PatientDoctorReferrals/Index")
    public String PatientIndex(Model model){
//        Iterable<DoctorReferral> doctorReferrals = doctorReferralService.getAll();
//        model.addAttribute("doctorReferrals",doctorReferrals);
//        return "/DoctorReferrals/Index";
        return patientFindPaginated(1, "dateOfTaking" , "desc",model);

    }

    @GetMapping("/PatientDoctorReferrals/Index/{pageNumber}")
    public String patientFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<DoctorReferral> page = doctorReferralService.patientFindPaginated(pageNumber,pageSize,sortField,sortDir);
        List<DoctorReferral> doctorReferrals = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("doctorReferrals", doctorReferrals);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");

        return "/PatientDoctorReferrals/Index";
    }
    @GetMapping("/DoctorReferrals/Create")
    public String ShowCreate(Model model){
        DoctorReferral doctorReferral = new DoctorReferral();
        model.addAttribute("doctorReferral",doctorReferral);
        return "/DoctorReferrals/Create";
    }
    @PostMapping("/DoctorReferrals/Create")
    public String Create(@ModelAttribute("doctorReferral")DoctorReferral doctorReferral){
        doctorReferralService.add(doctorReferral);
        return "redirect:/DoctorReferrals/Index";
    }
    @GetMapping("DoctorReferrals/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<DoctorReferral> doctorReferral = doctorReferralService.getById(id);
        if (doctorReferral.isPresent()){
            model.addAttribute("doctorReferral",doctorReferral.get());
            return "/DoctorReferrals/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("DoctorReferrals/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        doctorReferralService.delete(id);
        return "redirect:/DoctorReferrals/Index";
    }
    @GetMapping("DoctorReferrals/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<DoctorReferral> doctorReferral = doctorReferralService.getById(id);
        if (doctorReferral.isPresent()){
            model.addAttribute("doctorReferral",doctorReferral.get());
            return "/DoctorReferrals/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
}
