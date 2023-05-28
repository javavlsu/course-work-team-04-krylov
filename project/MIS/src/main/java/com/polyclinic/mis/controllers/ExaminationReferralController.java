package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.*;
import com.polyclinic.mis.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class ExaminationReferralController {
    @Autowired
    ExaminationReferralServiceImpl examinationReferralService;

    @Autowired
    PatientServiceImpl patientService;

    @Autowired
    DiagnosisServiceImpl diagnosisService;

    @Autowired
    DoctorServiceImpl doctorService;

    @Autowired
    ExaminationCabinetServiceImpl examinationCabinetService;

    @Autowired
    ExaminationCabinetReferralTimeServiceImpl examinationCabinetReferralTimeService;
    @GetMapping("/ExaminationReferrals/Index")
    public String Index(Model model){
        return findPaginated(1, "dateOfTaking" , "desc","","",model);
    }

    @GetMapping("/ExaminationReferrals/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;
        Page<ExaminationReferral> page = examinationReferralService.findPaginated(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
        List<ExaminationReferral> examinationReferrals = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("examinationReferrals", examinationReferrals);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);
        model.addAttribute("currentPageLink","ExaminationReferrals");



        return "/ExaminationReferrals/Index";
    }


    @GetMapping("/FunctionalDoctorExaminationReferrals/Index")
    public String FunctionalDoctorIndex(Model model){
//        Iterable<AnalysisReferral> analysisReferrals = analysisReferralService.getAll();
//        model.addAttribute("analysisReferrals",analysisReferrals);
//        return "/AnalysisReferrals/Index";
        return functionalDoctorFindPaginated(1, "status" , "asc","","",model);
    }

    @GetMapping("/FunctionalDoctorExaminationReferrals/Index/{pageNumber}")
    public String functionalDoctorFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<ExaminationReferral> page = examinationReferralService.findPaginatedForCabinet(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
        List<ExaminationReferral> examinationReferrals = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("examinationReferrals", examinationReferrals);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);


        model.addAttribute("currentPageLink","FunctionalDoctorExaminationReferrals");


        return "/ExaminationReferrals/Index";
    }

    @GetMapping("/PatientExaminationReferrals/Index")
    public String PatientIndex(Model model){
        return patientFindPaginated(1, "dateOfTaking" , "desc",model);
    }

    @GetMapping("/PatientExaminationReferrals/Index/{pageNumber}")
    public String patientFindPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<ExaminationReferral> page = examinationReferralService.patientFindPaginated(pageNumber,pageSize,sortField,sortDir);
        List<ExaminationReferral> examinationReferrals = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("examinationReferrals", examinationReferrals);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");

        return "/PatientExaminationReferrals/Index";
    }

    @GetMapping("/ExaminationReferrals/Create")
    public String ShowCreateWithoutParam(Model model){
        ExaminationReferral examinationReferral = new ExaminationReferral();
        model.addAttribute("examinationReferral",examinationReferral);

        var patients = patientService.getAll();
        model.addAttribute("patients",patients);
        var diagnoses = diagnosisService.getAll();
        model.addAttribute("diagnoses",diagnoses);

        Doctor doctor = doctorService.currentDoctor();
        model.addAttribute("doctorId",doctor.getId());
//        model.addAttribute("currentStatus","Выписано");

        LocalDate startOfAppointment = LocalDate.now();
        model.addAttribute("startOfAppointment",startOfAppointment);
        LocalDate endOfAppointment = LocalDate.now().plusDays(7);
        model.addAttribute("endOfAppointment",endOfAppointment);

        var cabinets = examinationCabinetService.getAll();
        model.addAttribute("cabinets", cabinets);


        model.addAttribute("appointmentTaken", false);

        //todo
        //todo
        return "/ExaminationReferrals/Create";
    }

    @GetMapping("/ExaminationReferrals/Create/{patientId}")
    public String ShowCreate(@PathVariable(required = false) Long patientId,Model model){
        ExaminationReferral examinationReferral = new ExaminationReferral();
        model.addAttribute("examinationReferral",examinationReferral);

        var diagnoses = diagnosisService.getAll();
        model.addAttribute("diagnoses",diagnoses);

        Doctor doctor = doctorService.currentDoctor();
        model.addAttribute("doctorId",doctor.getId());
//        model.addAttribute("currentStatus","Выписано");

        LocalDate startOfAppointment = LocalDate.now();
        model.addAttribute("startOfAppointment",startOfAppointment);
        LocalDate endOfAppointment = LocalDate.now().plusDays(7);
        model.addAttribute("endOfAppointment",endOfAppointment);

        var cabinets = examinationCabinetService.getAll();
        model.addAttribute("cabinets", cabinets);


        model.addAttribute("appointmentTaken", false);


        return "/ExaminationReferrals/Create";
    }

    @PostMapping("/ExaminationReferrals/Create")
    public String Create(@ModelAttribute("examinationReferral")ExaminationReferral examinationReferral){
        examinationReferral.setStatus("Выписано");
        examinationReferral.setDateOfIssue(LocalDateTime.now());
        examinationReferralService.add(examinationReferral);
        return "redirect:/ExaminationReferrals/Index";
    }



    @ResponseBody
    @GetMapping("/GetExaminationReferralTimes/{cabinetId}")
    public List<?> getAppointmentTimes(
            @PathVariable(value = "cabinetId") long cabinetId,
            @RequestParam(value = "dateChosen") Date dateChosen) {
        /*
        Locale locale = new Locale("ru","RU");
        DateFormat formatter = new SimpleDateFormat("EEEE", locale);
        String dayOfTheWeek = formatter.format(dateChosen);
        dayOfTheWeek = dayOfTheWeek.substring(0,1).toUpperCase(locale)+dayOfTheWeek.substring(1);
//        var therapistAppointmentTimeList = therapistAppointmentTimeService.getByDoctorId(doctorId);

        */
        var examinationReferralTimeList = examinationCabinetReferralTimeService.getByCabinetId(cabinetId);


//        var therapistAppointmentTimeList = therapistAppointmentTimeService.getByDoctorIdAndWeekDay(doctorId,dayOfTheWeek);

        var examinationReferrals = examinationReferralService.getByCabinetIdAndDate(cabinetId,dateChosen);

//        var therapistAppointments = doctorAppointmentService.getByDoctorIdAndDate(doctorId,dateChosen);


        List<String> times = new ArrayList<>();

//        times.add(dayOfTheWeek);

        for (int i = 0; i<examinationReferrals.size();i++) {
            for (int j = 0; j < examinationReferralTimeList.size(); j++) {
                if (examinationReferrals.get(i).getTime().equals(examinationReferralTimeList.get(j).getTime())){
                    examinationReferralTimeList.remove(j);
                }
            }
        }
        for (int i = 0; i<examinationReferralTimeList.size();i++){
            times.add(examinationReferralTimeList.get(i).getTime().toString());
        }

//        return therapistAppointmentTimeService.getAll();
        return times;

    }






    @GetMapping("ExaminationReferrals/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<ExaminationReferral> examinationReferral = examinationReferralService.getById(id);
        if (examinationReferral.isPresent()){
            model.addAttribute("examinationReferral",examinationReferral.get());
            return "/ExaminationReferrals/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("ExaminationReferrals/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        examinationReferralService.delete(id);
        return "redirect:/ExaminationReferrals/Index";
    }
    @GetMapping("ExaminationReferrals/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<ExaminationReferral> examinationReferral = examinationReferralService.getById(id);
        if (examinationReferral.isPresent()){
            model.addAttribute("examinationReferral",examinationReferral.get());
            return "/ExaminationReferrals/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("PatientExaminationReferrals/Details/{id}")
    public String PatientDetails(@PathVariable (value = "id") long id, Model model){
        Optional<ExaminationReferral> examinationReferral = examinationReferralService.getById(id);
        if (examinationReferral.isPresent()){
            if(examinationReferral.get().getPatient().getId()==patientService.currentPatient().getId()) {

                model.addAttribute("examinationReferral", examinationReferral.get());
                return "/ExaminationReferrals/Details";
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




    @GetMapping("FunctionalDoctorExaminationReferrals/CreateExamination/{id}")
    public String CreateExamination(@PathVariable (value = "id")long id){
        Optional<ExaminationReferral> examinationReferral = examinationReferralService.getById(id);
        if (examinationReferral.isPresent()){
//            analysisReferral.get().setStatus("Пройдено");
//            analysisReferral.get().setDateOfTaking(LocalDateTime.now());

//            analysisReferralService.edit(analysisReferral.get());
            return "redirect:/FunctionalDoctorExaminations/Create/"+examinationReferral.get().getId();
        }
        else {
            //todo
            return "/Error";
        }
    }
}
