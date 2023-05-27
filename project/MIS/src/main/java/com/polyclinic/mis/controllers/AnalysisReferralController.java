package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.*;
import com.polyclinic.mis.service.impl.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class AnalysisReferralController {
    @Autowired
    AnalysisReferralServiceImpl analysisReferralService;

    @Autowired
    PatientServiceImpl patientService;

    @Autowired
    DiagnosisServiceImpl diagnosisService;

    @Autowired
    DoctorServiceImpl doctorService;

    @Autowired
    AnalysisCabinetServiceImpl analysisCabinetService;
    @GetMapping("/AnalysisReferrals/Index")
    public String Index(Model model){
//        Iterable<AnalysisReferral> analysisReferrals = analysisReferralService.getAll();
//        model.addAttribute("analysisReferrals",analysisReferrals);
//        return "/AnalysisReferrals/Index";
        return findPaginated(1, "status" , "asc","","",model);
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
        model.addAttribute("currentPage","AnalysisReferrals");

        return "/AnalysisReferrals/Index";
    }
    @GetMapping("/AssistantAnalysisReferrals/Index")
    public String AssistantIndex(Model model){
//        Iterable<AnalysisReferral> analysisReferrals = analysisReferralService.getAll();
//        model.addAttribute("analysisReferrals",analysisReferrals);
//        return "/AnalysisReferrals/Index";
        return assistantFindPaginated(1, "status" , "asc","","",model);
    }

    @GetMapping("/AssistantAnalysisReferrals/Index/{pageNumber}")
    public String assistantFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<AnalysisReferral> page = analysisReferralService.findPaginatedForCabinet(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
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
        model.addAttribute("currentPage","AssistantAnalysisReferrals");


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

        var patients = patientService.getAll();
        model.addAttribute("patients",patients);
        var diagnoses = diagnosisService.getAll();
        model.addAttribute("diagnoses",diagnoses);

        Doctor doctor = doctorService.currentDoctor();
        model.addAttribute("doctorId",doctor.getId());

        var cabinets = analysisCabinetService.getAll();
        model.addAttribute("cabinets",cabinets);
//        model.addAttribute("currentStatus","Выписано");



        return "/AnalysisReferrals/Create";
    }
    @GetMapping("/AnalysisReferrals/Create/{patientId}")
    public String ShowCreate(@PathVariable(required = false) Long patientId, Model model){
        AnalysisReferral analysisReferral = new AnalysisReferral();
        model.addAttribute("analysisReferral",analysisReferral);
        var diagnoses = diagnosisService.getAll();
        model.addAttribute("diagnoses",diagnoses);

        Doctor doctor = doctorService.currentDoctor();
        model.addAttribute("doctorId",doctor.getId());

        var cabinets = analysisCabinetService.getAll();
        model.addAttribute("cabinets",cabinets);
//        model.addAttribute("currentStatus","Выписано");
        return "/AnalysisReferrals/Create";
    }
    @PostMapping("/AnalysisReferrals/Create")
    public String Create(@Valid @ModelAttribute("analysisReferral")AnalysisReferral analysisReferral,
                         BindingResult result,
                         Model model){
        if (result.hasErrors()){
            model.addAttribute("analysisReferral",analysisReferral);
            var patients = patientService.getAll();
            model.addAttribute("patients",patients);
            var diagnoses = diagnosisService.getAll();
            model.addAttribute("diagnoses",diagnoses);
            Doctor doctor = doctorService.currentDoctor();
            model.addAttribute("doctorId",doctor.getId());
            var cabinets = analysisCabinetService.getAll();
            model.addAttribute("cabinets",cabinets);
            if (analysisReferral.getPatient().equals(null)){
                return "/AnalysisReferrals/Create";
            }
            else {
                return "/AnalysisReferrals/Create/"+analysisReferral.getPatient().getId();
            }

        }
        else {
            analysisReferral.setStatus("Выписано");
            analysisReferral.setDateOfIssue(LocalDateTime.now());
            analysisReferralService.add(analysisReferral);
            return "redirect:/AnalysisReferrals/Index";
        }
    }
    @GetMapping("AnalysisReferrals/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<AnalysisReferral> analysisReferral = analysisReferralService.getById(id);
        if (analysisReferral.isPresent()){
            model.addAttribute("analysisReferral",analysisReferral.get());
            var patients = patientService.getAll();
            model.addAttribute("patients",patients);
            var diagnoses = diagnosisService.getAll();
            model.addAttribute("diagnoses",diagnoses);
            Doctor doctor = doctorService.currentDoctor();
            model.addAttribute("doctorId",doctor.getId());
            var cabinets = analysisCabinetService.getAll();
            model.addAttribute("cabinets",cabinets);

            return "/AnalysisReferrals/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }

    @PostMapping("AnalysisReferrals/Edit")
    public String Edit(@Valid @ModelAttribute("analysisReferral") AnalysisReferral analysisReferral,
                       BindingResult result,
                       Model model){
        if (result.hasErrors()){
            model.addAttribute("analysisReferral",analysisReferral);
            var patients = patientService.getAll();
            model.addAttribute("patients",patients);
            var diagnoses = diagnosisService.getAll();
            model.addAttribute("diagnoses",diagnoses);
            Doctor doctor = doctorService.currentDoctor();
            model.addAttribute("doctorId",doctor.getId());
            var cabinets = analysisCabinetService.getAll();
            model.addAttribute("cabinets",cabinets);
            return "AnalysisReferrals/Edit/"+analysisReferral.getId();
        }
        else {
            analysisReferralService.edit(analysisReferral);
            return "redirect:/AnalysisReferrals/Index";
        }
    }

//    @PostMapping("DoctorAppointmentTimes/Edit")
//    public String Edit(@ModelAttribute("doctorAppointmentTime") DoctorAppointmentTime doctorAppointmentTime){
//        Time time = Time.valueOf(doctorAppointmentTime.getTimeString()+":00");
//        doctorAppointmentTime.setTime(time);
//        doctorAppointmentTimeService.edit(doctorAppointmentTime);
//        return "redirect:/DoctorAppointmentTimes/Index";
//    }
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
    @GetMapping("AssistantAnalysisReferrals/CreateAnalysis/{id}")
    public String CreateAnalysis(@PathVariable (value = "id")long id){
        Optional<AnalysisReferral> analysisReferral = analysisReferralService.getById(id);
        if (analysisReferral.isPresent()){
//            analysisReferral.get().setStatus("Пройдено");
//            analysisReferral.get().setDateOfTaking(LocalDateTime.now());

//            analysisReferralService.edit(analysisReferral.get());
            return "redirect:/AssistantAnalyses/Create/"+analysisReferral.get().getId();
        }
        else {
            //todo
            return "/Error";
        }
    }

}
