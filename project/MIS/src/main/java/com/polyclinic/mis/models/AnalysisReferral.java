package com.polyclinic.mis.models;

import java.sql.Date;

public class AnalysisReferral {
    public long id;
    public String diagnosisId;
    public Diagnosis diagnosis;
    public int doctorId;
    public Doctor doctor;
    public int patientId;
    public Patient patient;
    public String type;
    public int assistantId;
    public Assistant assistant;
    public String cabinetNum;
    public Date dateTime;

}
