package com.polyclinic.mis.controllers;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.*;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.AnalysisServiceImpl;
import com.polyclinic.mis.service.impl.AssistantServiceImpl;
import com.polyclinic.mis.service.impl.PatientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class AnalysisController {
    @Autowired
    AnalysisServiceImpl analysisService;
    @Autowired
    UserService userService;
    @Autowired
    AssistantServiceImpl assistantService;
    @Autowired
    PatientServiceImpl patientService;

    @Autowired
    AnalysisReferralServiceImpl analysisReferralService;
    @GetMapping("/Analyses/Index")
    public String Index(Model model){
//        Iterable<Analysis> analyses = analysisService.getAll();
//        model.addAttribute("analyses",analyses);
//        return "/Analyses/Index";
        return findPaginated(1, "date" , "desc","","",model);
    }

    @GetMapping("/Analyses/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Analysis> page = analysisService.findPaginated(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
        List<Analysis> analysisList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("analyses", analysisList);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);

        model.addAttribute("currentPageLink","Analyses");

        return "/Analyses/Index";
    }
    @GetMapping("/AssistantAnalyses/Index")
    public String AssistantIndex(Model model){
//        Iterable<Analysis> analyses = analysisService.getAll();
//        model.addAttribute("analyses",analyses);
//        return "/Analyses/Index";
        return assistantFindPaginated(1, "date" , "desc","","",model);
    }
    @GetMapping("/AssistantAnalyses/Index/{pageNumber}")
    public String assistantFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Analysis> page = analysisService.findPaginatedForCabinet(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
        List<Analysis> analysisList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("analyses", analysisList);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);

        model.addAttribute("currentPageLink","AssistantAnalyses");

        return "/Analyses/Index";
    }


    @GetMapping("/PatientAnalyses/Index")
    public String PatientIndex(Model model){
//        Iterable<Analysis> analyses = analysisService.getAll();
//        model.addAttribute("analyses",analyses);
//        return "/Analyses/Index";
        return PatientFindPaginated(1, "date" , "desc",model);
    }

    @GetMapping("/PatientAnalyses/Index/{pageNumber}")
    public String PatientFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<Analysis> page = analysisService.patientFindPaginated(pageNumber,pageSize,sortField,sortDir);
        List<Analysis> analysisList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("analyses", analysisList);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");


        return "/PatientAnalyses/Index";
    }



    @GetMapping("/AssistantAnalyses/Create")
    public String ShowCreateWithoutParam(Model model){
        Analysis analysis = new Analysis();
        model.addAttribute("analysis",analysis);

        var patients = patientService.getAll();
        model.addAttribute("patients",patients);

        Assistant assistant = assistantService.currentAssistant();
        model.addAttribute("assistantId",assistant.getId());

        model.addAttribute("currentDate",LocalDateTime.now());

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        var user = userService.findUserByEmail(authentication.getName());
//        model.addAttribute("user",user);

        return "/Analyses/Create";
    }

    @GetMapping("/AssistantAnalyses/Create/{referralId}")
    public String ShowCreate(@PathVariable(required = false) Long referralId,
                             Model model){
        Analysis analysis = new Analysis();
        model.addAttribute("analysis",analysis);
        AnalysisReferral analysisReferral = analysisReferralService.getById(referralId).get();

        //todo раскомменитровать
//        analysisReferral.setStatus("Пройдено");
        analysisReferral.setDateOfTaking(LocalDateTime.now());
        analysisReferralService.edit(analysisReferral);

        model.addAttribute("patientId",analysisReferral.getPatient().getId());

        Assistant assistant = assistantService.currentAssistant();
        model.addAttribute("assistantId",assistant.getId());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("currentDate",dateTimeFormatter.format(now));

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        var user = userService.findUserByEmail(authentication.getName());
//        model.addAttribute("user",user);

        return "/Analyses/Create";
    }
    @PostMapping("/AssistantAnalyses/Create")
    public String Create(@Valid @ModelAttribute("analysis")Analysis analysis,
                         BindingResult result,
                         Model model){
        if (result.hasErrors()){
            model.addAttribute("analysis",analysis);

            Assistant assistant = assistantService.currentAssistant();
            model.addAttribute("assistantId",assistant);


            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime now = LocalDateTime.now();
            model.addAttribute("currentDate",dateTimeFormatter.format(now));
            if (analysis.getPatient().equals(null)) {
                return "/Analyses/Create";
            }
            else {
                return "/Analyses/Create/"+analysis.getPatient().getId();
            }

        }
        else {
            analysisService.add(analysis);
            return "redirect:/AssistantAnalyses/Index";
        }
    }
    @GetMapping("AssistantAnalyses/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Analysis> analysis = analysisService.getById(id);
        if (analysis.isPresent()){

            if(analysis.get().getAnalysisReferral().getCabinet().getId()==assistantService.currentAssistant().getCabinet().getId()) {

                model.addAttribute("analysis", analysis.get());
                model.addAttribute("currentPageLink", "AssistantAnalyses");
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime now = LocalDateTime.now();
                model.addAttribute("currentDate", dateTimeFormatter.format(now));

                return "/Analyses/Update";
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
    @PostMapping("AssistantAnalyses/Edit")
    public String Edit(@Valid @ModelAttribute("analysis") Analysis analysis,
                       BindingResult result,
                       Model model){

        if (result.hasErrors()){
            model.addAttribute("analysis",analysis);
            model.addAttribute("currentPageLink","AssistantAnalyses");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime now = LocalDateTime.now();
            model.addAttribute("currentDate",dateTimeFormatter.format(now));

            return "AnalysisReferrals/Edit/"+analysis.getId();
        }
        else {
            analysisService.edit(analysis);
            return "redirect:/AssistantAnalyses/Index";
        }
    }

//    @GetMapping("AssistantAnalyses/Edit")
//    public String Edit(@PathVariable(value = "id") long id, Model model){
//        Optional<Analysis> analysis = analysisService.getById(id);
//        if (analysis.isPresent()){
//            model.addAttribute("analysis",analysis.get());
//            return "/Analyses/Update";
//        }
//        else {
//            //todo
//            return "/Error";
//        }
//    }
    @GetMapping("AssistantAnalyses/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        analysisService.delete(id);
        return "redirect:/AssistantAnalyses/Index";
    }
    @GetMapping("AssistantAnalyses/Details/{id}")
    public String AssistantDetails(@PathVariable (value = "id") long id, Model model){
        Optional<Analysis> analysis = analysisService.getById(id);
        if (analysis.isPresent()){
            model.addAttribute("analysis",analysis.get());
            model.addAttribute("currentPageLink","AssistantAnalyses");

            return "/Analyses/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Analyses/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Analysis> analysis = analysisService.getById(id);
        if (analysis.isPresent()){
            model.addAttribute("analysis",analysis.get());

            model.addAttribute("currentPageLink","Analyses");

            return "/Analyses/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("PatientAnalyses/Details/{id}")
    public String PatientDetails(@PathVariable (value = "id") long id, Model model){
        Optional<Analysis> analysis = analysisService.getById(id);
        if (analysis.isPresent()){
            if(patientService.currentPatient().getId()!=analysis.get().getPatient().getId()){
                return "/Error";
            }
            else {
                model.addAttribute("analysis", analysis.get());
                return "/PatientAnalyses/Details";
            }
        }
        else {
            //todo
            return "/Error";
        }
    }
}
