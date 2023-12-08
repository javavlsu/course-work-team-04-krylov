package com.polyclinic.mis.controllers.rest;

import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.DoctorAppointment;
import com.polyclinic.mis.models.rest.MakeAppointmentRequest;
import com.polyclinic.mis.service.DoctorService;
import com.polyclinic.mis.service.TherapistAppointmentTimeService;
import com.polyclinic.mis.service.impl.DoctorAppointmentServiceImpl;
import com.polyclinic.mis.service.impl.PatientServiceImpl;
import com.polyclinic.mis.service.impl.TherapistAppointmentTimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class DoctorRestController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    TherapistAppointmentTimeServiceImpl therapistAppointmentTimeService;
    @Autowired
    DoctorAppointmentServiceImpl doctorAppointmentService;
    @Autowired
    PatientServiceImpl patientService;

    @GetMapping("/doctors")
    public List<Doctor> doctorsGetAll(){
//
//        List<Doctor> doctorList = doctorService.getAll();
        return doctorService.getAll();
    }
    @GetMapping("/doctors/{id}")
    public Doctor doctorGetById(@PathVariable long id){
        return doctorService.getById(id).get();
    }
    @GetMapping("/doctors/getAllTherapists")
    public List<Doctor> getAllTherapists(){
        return doctorService.getAllTherapists();
    }
    @GetMapping("/doctors/GetAppointmentTimes/{doctorId}")
    public List<String> getAppointmentTimes(
            @PathVariable(value = "doctorId") long doctorId,
            @RequestParam(value = "dateChosen") Date dateChosen){

        Locale locale = new Locale("ru","RU");
        DateFormat formatter = new SimpleDateFormat("EEEE", locale);
        String dayOfTheWeek = formatter.format(dateChosen);
        dayOfTheWeek = dayOfTheWeek.substring(0,1).toUpperCase(locale)+dayOfTheWeek.substring(1);
        var therapistAppointmentTimeList = therapistAppointmentTimeService.getByDoctorIdAndWeekDay(doctorId,dayOfTheWeek);
        var therapistAppointments = doctorAppointmentService.getByDoctorIdAndDate(doctorId,dateChosen);
        List<String> times = new ArrayList<>();
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
//        System.out.println(times);
        return times;
    }
    @PostMapping("/doctors/MakeAppointment")
    public Boolean makeAppointment(@RequestBody MakeAppointmentRequest makeAppointmentRequest){
        var therapistAppointments = doctorAppointmentService
                .getByDoctorIdAndDateAndTime(
                        makeAppointmentRequest.getDoctorId(),
                        makeAppointmentRequest.getAppointmentDate(),
                        Time.valueOf(makeAppointmentRequest.getAppointmentTime()));
        if (therapistAppointments.size()==0){
            DoctorAppointment doctorAppointment = new DoctorAppointment();
            //todo что то сделать уже с optinoal
            doctorAppointment.setDoctor(doctorService.getById(makeAppointmentRequest.getDoctorId()).get());
            doctorAppointment.setDate(makeAppointmentRequest.getAppointmentDate());
            doctorAppointment.setTime(Time.valueOf(makeAppointmentRequest.getAppointmentTime()));
            doctorAppointment.setPatient(patientService.getByEmail(makeAppointmentRequest.getPatientEmail()).get());
            doctorAppointment.setStatus("Создана");

            doctorAppointmentService.add(doctorAppointment);

            try {
                doctorAppointmentService.sendEmail(doctorAppointment);
            }
            catch (Exception e){

            }
            return true;
        }
        else {
            return false;
        }

    }
}
