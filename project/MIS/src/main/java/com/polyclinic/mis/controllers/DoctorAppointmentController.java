package com.polyclinic.mis.controllers;

import com.polyclinic.mis.models.*;
import com.polyclinic.mis.repository.PatientRepository;
import com.polyclinic.mis.repository.PolyclinicUserRepository;
import com.polyclinic.mis.service.DoctorAppointmentService;
import com.polyclinic.mis.service.TherapistAppointmentTimeService;
import com.polyclinic.mis.service.impl.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
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
public class DoctorAppointmentController {
    @Autowired
    DoctorAppointmentServiceImpl doctorAppointmentService;
    @Autowired
    PolyclinicUserServiceImpl polyclinicUserService;
    @Autowired
    DoctorServiceImpl doctorService;

    @Autowired
    TherapistAppointmentTimeServiceImpl therapistAppointmentTimeService;

    @GetMapping("/DoctorAppointments/Index")
    public String Index(Model model) {
//        Iterable<DoctorReferralAppointment> doctorAppointments = doctorAppointmentService.getAll();
//        model.addAttribute("doctorAppointments",doctorAppointments);
//        return "/DoctorAppointments/Index";
        return findPaginated(1, "status", "desc", "", "", "", model);
    }

    @GetMapping("/DoctorAppointments/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable(value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            @RequestParam(value = "status") String status,
            Model model) {
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<DoctorAppointment> page = doctorAppointmentService.findPaginated(pageNumber, pageSize, sortField, sortDir, patientFio, patientBirthDate, status);
        List<DoctorAppointment> doctorAppointments = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("doctorAppointments", doctorAppointments);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("patientFIO", patientFio);
        model.addAttribute("patientBirthDate", patientBirthDate);
        model.addAttribute("status", status);


        return "/DoctorAppointments/Index";
    }

    @GetMapping("/PatientDoctorAppointments/Index")
    public String PatientIndex(Model model) {
        return patientFindPaginated(1, "status", "desc", "", model);
    }

    @GetMapping("/PatientDoctorAppointments/Index/{pageNumber}")
    public String patientFindPaginated(
            @PathVariable(value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "status") String status,
            Model model) {
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;


        Page<DoctorAppointment> page = doctorAppointmentService.patientFindPaginated(pageNumber, pageSize, sortField, sortDir, status);
        List<DoctorAppointment> doctorAppointments = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("doctorAppointments", doctorAppointments);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//        model.addAttribute("patientFIO",patientFio);
//        model.addAttribute("patientBirthDate",patientBirthDate);
        model.addAttribute("status", status);


        return "/PatientDoctorAppointments/Index";
    }

    // ===========================================================
    @GetMapping("/PatientDoctorAppointments/Create")
    public String ShowCreate(Model model) {
        DoctorAppointment doctorAppointment = new DoctorAppointment();
        model.addAttribute("doctorAppointment", doctorAppointment);

        Patient patient = polyclinicUserService.getPatientFromContext();

        var doctors = doctorService.getAllTherapists();
        model.addAttribute("patientId", patient.getId());
        model.addAttribute("doctors", doctors);
        LocalDate startOfAppointment = LocalDate.now();
        model.addAttribute("startOfAppointment",startOfAppointment);
        LocalDate endOfAppointment = LocalDate.now().plusDays(7);
        model.addAttribute("endOfAppointment",endOfAppointment);


        model.addAttribute("appointmentTaken", false);

        return "/PatientDoctorAppointments/Create";
    }

    @PostMapping("/PatientDoctorAppointments/Create")
    public String Create(@Valid @ModelAttribute("doctorAppointment") DoctorAppointment doctorAppointment,
                         BindingResult result,
                         Model model) throws UnsupportedEncodingException {
        if (result.hasErrors()){
            doctorAppointment.setDoctor(null);
            model.addAttribute("doctorAppointment",doctorAppointment);
            Patient patient = polyclinicUserService.getPatientFromContext();
            var doctors = doctorService.getAllTherapists();
            model.addAttribute("patientId", patient.getId());
            model.addAttribute("doctors", doctors);
            LocalDate startOfAppointment = LocalDate.now();
            model.addAttribute("startOfAppointment",startOfAppointment);
            LocalDate endOfAppointment = LocalDate.now().plusDays(7);
            model.addAttribute("endOfAppointment",endOfAppointment);

            model.addAttribute("appointmentTaken", false);

            return "/PatientDoctorAppointments/Create";
        }
        else {
            Boolean taken = false;
            var therapistAppointments = doctorAppointmentService.getByDoctorIdAndDateAndTime(doctorAppointment.getDoctor().getId(),doctorAppointment.getDate(),doctorAppointment.getTime());
            if (therapistAppointments.size()==0){
                doctorAppointment.setStatus("Создана");
                doctorAppointmentService.add(doctorAppointment);

                try {
                    doctorAppointmentService.sendEmail(doctorAppointment);
                }
                catch (Exception e){
                    return "redirect:/PatientDoctorAppointments/Index";
                }


                return "redirect:/PatientDoctorAppointments/Index";
            }

            else {
                doctorAppointment.setDoctor(null);
                model.addAttribute("doctorAppointment",doctorAppointment);
                Patient patient = polyclinicUserService.getPatientFromContext();
                var doctors = doctorService.getAllTherapists();
                model.addAttribute("patientId", patient.getId());
                model.addAttribute("doctors", doctors);
                LocalDate startOfAppointment = LocalDate.now();
                model.addAttribute("startOfAppointment",startOfAppointment);
                LocalDate endOfAppointment = LocalDate.now().plusDays(7);
                model.addAttribute("endOfAppointment",endOfAppointment);

                model.addAttribute("appointmentTaken", true);
                return "/PatientDoctorAppointments/Create";
            }

        }
    }

    @ResponseBody
    @GetMapping("/GetAppointmentTimes/{doctorId}")
    public List<?> getAppointmentTimes(
            @PathVariable(value = "doctorId") long doctorId,
            @RequestParam(value = "dateChosen")Date dateChosen) {
        Locale locale = new Locale("ru","RU");
        DateFormat formatter = new SimpleDateFormat("EEEE", locale);
        String dayOfTheWeek = formatter.format(dateChosen);
        dayOfTheWeek = dayOfTheWeek.substring(0,1).toUpperCase(locale)+dayOfTheWeek.substring(1);
//        var therapistAppointmentTimeList = therapistAppointmentTimeService.getByDoctorId(doctorId);

        var therapistAppointmentTimeList = therapistAppointmentTimeService.getByDoctorIdAndWeekDay(doctorId,dayOfTheWeek);

        var therapistAppointments = doctorAppointmentService.getByDoctorIdAndDate(doctorId,dateChosen);
        List<String> times = new ArrayList<>();

//        times.add(dayOfTheWeek);

        for (int i = 0; i<therapistAppointments.size();i++) {
            for (int j = 0; j < therapistAppointmentTimeList.size(); j++) {
                if (therapistAppointments.get(i).getTime().equals(therapistAppointmentTimeList.get(j).getTime())){
                    therapistAppointmentTimeList.remove(j);
                }
            }
        }
        for (int i = 0; i<therapistAppointmentTimeList.size();i++){
            times.add(therapistAppointmentTimeList.get(i).getTime().toString());
        }

//        return therapistAppointmentTimeService.getAll();
        return times;

    }


    @GetMapping("DoctorAppointments/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model) {
        Optional<DoctorAppointment> doctorAppointment = doctorAppointmentService.getById(id);
        if (doctorAppointment.isPresent()) {
            model.addAttribute("doctorAppointment", doctorAppointment.get());
            return "/DoctorAppointments/Update";
        } else {
            //todo
            return "/Error";
        }
    }

    @GetMapping("DoctorAppointments/Delete/{id}")
    public String Delete(@PathVariable(value = "id") long id, Model model) {
        doctorAppointmentService.delete(id);
        return "redirect:/DoctorAppointments/Index";
    }

    @GetMapping("DoctorAppointments/Details/{id}")
    public String Details(@PathVariable(value = "id") long id, Model model) {
        Optional<DoctorAppointment> doctorAppointment = doctorAppointmentService.getById(id);
        if (doctorAppointment.isPresent()) {
            model.addAttribute("doctorAppointment", doctorAppointment.get());
            return "/DoctorAppointments/Details";
        } else {
            //todo
            return "/Error";
        }
    }

    @GetMapping("CreateInspection/{id}")
    public String CreateInspection(@PathVariable(value = "id") long id, Model model) {
        Optional<DoctorAppointment> doctorAppointment = doctorAppointmentService.getById(id);
        if (doctorAppointment.isPresent()) {
            doctorAppointment.get().setStatus("Использована");
            doctorAppointmentService.edit(doctorAppointment.get());
            return "redirect:/Inspections/Create/"+doctorAppointment.get().getPatient().getId();
        } else {
            //todo
            return "/Error";
        }
    }
}
