package com.polyclinic.mis.models;

import java.sql.Date;

public class DoctorReferral {
    public int Id;
    public String DiagnosisId;
    public Diagnosis Diagnosis;
    public int DoctorIdInitial;
    public Doctor DoctorInitial;
    public int PatientId;
    public Patient Patient;
    public String Type;
    public int DoctorIdTarget;
    public Doctor DoctorTarget;
    public String СabinetNum;
    public Date DateTime;


}
