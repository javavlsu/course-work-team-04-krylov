package com.polyclinic.mis.models;

import jakarta.persistence.*;

import java.sql.Date;
@Entity
public class DoctorAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private int PatientId;
    @ManyToOne
    private Patient Patient;
    private String CabinetId;
    private Date DateTime;
    private String Status;
    private int DoctorId;
    @ManyToOne
    private Doctor Doctor;
}
