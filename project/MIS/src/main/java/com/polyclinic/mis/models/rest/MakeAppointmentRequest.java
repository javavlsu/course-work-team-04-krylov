package com.polyclinic.mis.models.rest;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class MakeAppointmentRequest {
    private String patientEmail;
    private long doctorId;
    private Date appointmentDate;
    private String appointmentTime;
}
