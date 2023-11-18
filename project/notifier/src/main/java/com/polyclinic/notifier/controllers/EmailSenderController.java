package com.polyclinic.notifier.controllers;

import com.polyclinic.notifier.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Time;
import java.util.Date;

@Controller
public class EmailSenderController {

    @Autowired
    EmailSenderService emailSenderService;

    @ResponseBody
    @GetMapping("/SendDoctorAppointmentNotification/{email}")
    public String SendNotification(@PathVariable(value = "email") String email,
                                   @RequestParam(value = "date",required = false) String  date,
                                   @RequestParam(value = "time",required = false) String time,
                                   @RequestParam(value = "doctorLastName",required = false) String doctorLastName,
                                   @RequestParam(value = "doctorFirstName",required = false) String doctorFirstName,
                                   @RequestParam(value = "doctorMiddleName",required = false) String doctorMiddleName,
                                   @RequestParam(value = "cabinetName",required = false) String cabinetName,
                                   @RequestParam(value = "cabinetNumber",required = false) String cabinetNumber) {
        String subject = "Напоминание о приеме в поликлинике";
        String message = "Прием у врача "+doctorLastName+" "+doctorFirstName+" "+doctorMiddleName+"\nСостоится " + date +" в "+ time +"\nКабинет: "+cabinetName +"\nНомер кабинета: "+cabinetNumber;

        emailSenderService.sendDoctorAppointmentEmail(email,subject,message);
        return "Success";
    }

}
