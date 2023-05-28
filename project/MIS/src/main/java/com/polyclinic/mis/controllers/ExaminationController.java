package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.*;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.ExaminationReferralServiceImpl;
import com.polyclinic.mis.service.impl.ExaminationServiceImpl;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class ExaminationController {
    @Autowired
    ExaminationServiceImpl examinationService;

    @Autowired
    ExaminationReferralServiceImpl examinationReferralService;

    @Autowired
    FunctionalDiagnosticsDoctorServiceImpl functionalDiagnosticsDoctorService;
    @GetMapping("/Examinations/Index")
    public String Index(Model model){
//        Iterable<Examination> examinations = examinationService.getAll();
//        model.addAttribute("examinations",examinations);
//        return "/Examinations/Index";
        return findPaginated(1, "date" , "desc","","",model);
    }

    @GetMapping("/Examinations/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Examination> page = examinationService.findPaginated(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
        List<Examination> examinations = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("examinations", examinations);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);

        model.addAttribute("currentPageLink","Examinations");

        return "/Examinations/Index";
    }

    @GetMapping("/FunctionalDoctorExaminations/Index")
    public String AssistantIndex(Model model){
//        Iterable<Analysis> analyses = analysisService.getAll();
//        model.addAttribute("analyses",analyses);
//        return "/Analyses/Index";
        return functionalDoctorFindPaginated(1, "date" , "desc","","",model);
    }
    @GetMapping("/FunctionalDoctorExaminations/Index/{pageNumber}")
    public String functionalDoctorFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Examination> page = examinationService.findPaginatedForCabinet(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
        List<Examination> examinations = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("examinations", examinations);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);

        model.addAttribute("currentPageLink","FunctionalDoctorExaminations");

        return "/Examinations/Index";
    }

    @GetMapping("/PatientExaminations/Index")
    public String PatientIndex(Model model){
//        Iterable<Examination> examinations = examinationService.getAll();
//        model.addAttribute("examinations",examinations);
//        return "/Examinations/Index";
        return patientFindPaginated(1, "date" , "desc",model);
    }

    @GetMapping("/PatientExaminations/Index/{pageNumber}")
    public String patientFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Examination> page = examinationService.patientFindPaginated(pageNumber,pageSize,sortField,sortDir);
        List<Examination> examinations = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("examinations", examinations);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");

        model.addAttribute("currentPageLink","PatientExaminations");

        return "/PatientExaminations/Index";
    }

    @GetMapping("/Examinations/Create")
    public String ShowCreate(Model model){
        Examination examination = new Examination();
        model.addAttribute("examination",examination);
        return "/Examinations/Create";
    }
    @PostMapping("/Examinations/Create")
    public String Create(@ModelAttribute("examination")Examination examination){
        examinationService.add(examination);
        return "redirect:/Examinations/Index";
    }

//    @GetMapping("/FunctionalDoctorExaminations/Create")
//    public String FunctionalDoctorShowCreateWithoutParam(Model model){
//        Examination examination = new Examination();
//        model.addAttribute("examination",examination);
//        return "/Examinations/Create";
//    }


    @GetMapping("/FunctionalDoctorExaminations/Create/{referralId}")
    public String FunctionalDoctorShowCreate(@PathVariable(required = false) Long referralId, Model model){
        Examination examination = new Examination();
        model.addAttribute("examination",examination);


        ExaminationReferral examinationReferral = examinationReferralService.getById(referralId).get();
        examinationReferral.setStatus("Пройдено");
        examinationReferral.setDateOfTaking(LocalDateTime.now());
        examinationReferralService.edit(examinationReferral);

        model.addAttribute("patientId",examinationReferral.getPatient().getId());

        FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.currentFunctionalDiagnosticsDoctor();
        model.addAttribute("functionalDiagnosticsDoctorId",functionalDiagnosticsDoctor.getId());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("currentDate",dateTimeFormatter.format(now));

        model.addAttribute("currentPageLink","FunctionalDoctorExaminations");

        return "/Examinations/Create";
    }


    @PostMapping("/FunctionalDoctorExaminations/Create")
    public String FunctionalDoctorCreate(@Valid @ModelAttribute("examination")Examination examination,
                                         BindingResult result,
                                         Model model){
        if (result.hasErrors()){
            model.addAttribute("examination",examination);

            FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.currentFunctionalDiagnosticsDoctor();
            model.addAttribute("functionalDiagnosticsDoctorId",functionalDiagnosticsDoctor.getId());

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime now = LocalDateTime.now();
            model.addAttribute("currentDate",dateTimeFormatter.format(now));
            model.addAttribute("currentPageLink","FunctionalDoctorExaminations");

            if (examination.getPatient().equals(null)) {
                return "/Examinations/Create";

            }
            else {
                return "/Examinations/Create/"+examination.getPatient().getId();
            }
        }
        else {
            examinationService.add(examination);
            return "redirect:/FunctionalDoctorExaminations/Index";
        }

    }

    @GetMapping("FunctionalDoctorExaminations/Edit/{id}")
    public String FunctionalDoctorShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Examination> examination = examinationService.getById(id);
        if (examination.isPresent()){

            if(examination.get().getExaminationReferral().getCabinet().getId()==functionalDiagnosticsDoctorService.currentFunctionalDiagnosticsDoctor().getCabinet().getId()) {

                model.addAttribute("examination", examination.get());
                model.addAttribute("currentPageLink", "FunctionalDoctorExaminations");
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime now = LocalDateTime.now();
                model.addAttribute("currentDate", dateTimeFormatter.format(now));

                return "/Examinations/Update";
            }
            else {
                return "/Error";
            }
        }
        else {
            //todo
            return "/Error";
        }
    }
    @PostMapping("FunctionalDoctorExaminations/Edit")
    public String FunctionalDoctorEdit(@Valid @ModelAttribute("examination") Examination examination,
                       BindingResult result,
                       Model model){

        if (result.hasErrors()){
            model.addAttribute("examination",examination);
            model.addAttribute("currentPageLink","FunctionalDoctorExaminations");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime now = LocalDateTime.now();
            model.addAttribute("currentDate",dateTimeFormatter.format(now));

            return "FunctionalDoctorExaminations/Edit/"+examination.getId();
        }
        else {
            examinationService.edit(examination);
            return "redirect:/FunctionalDoctorExaminations/Index";
        }
    }

    @GetMapping("FunctionalDoctorExaminations/Delete/{id}")
    public String FunctionalDoctorDelete(@PathVariable (value = "id") long id, Model model){
        examinationService.delete(id);
        return "redirect:/FunctionalDoctorExaminations/Index";
    }
    @GetMapping("FunctionalDoctorExaminations/Details/{id}")
    public String FunctionalDoctorDetails(@PathVariable (value = "id") long id, Model model){
        Optional<Examination> analysis = examinationService.getById(id);
        if (analysis.isPresent()){
            model.addAttribute("analysis",analysis.get());
            model.addAttribute("currentPageLink","FunctionalDoctorExaminations");

            return "/Examinations/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }


    @GetMapping("Examinations/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Examination> examination = examinationService.getById(id);
        if (examination.isPresent()){
            model.addAttribute("examination",examination.get());
            return "/Examinations/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Examinations/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        examinationService.delete(id);
        return "redirect:/Examinations/Index";
    }
    @GetMapping("Examinations/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Examination> examination = examinationService.getById(id);
        if (examination.isPresent()){
            model.addAttribute("examination",examination.get());
            return "/Examinations/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }

}
