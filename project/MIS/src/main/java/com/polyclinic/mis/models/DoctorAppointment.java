package com.polyclinic.mis.models;

import java.sql.Date;

public class DoctorAppointment {
    public long Id;
    public int PatientId;
    public Patient Patient;
    public String CabinetId;
    public Date DateTime;
    public String Status;
    public int DoctorId;
    public Doctor Doctor;
}
