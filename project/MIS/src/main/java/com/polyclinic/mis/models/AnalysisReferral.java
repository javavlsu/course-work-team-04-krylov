package com.polyclinic.mis.models;

import java.sql.Date;

public class AnalysisReferral {
    public int Id;
    public String DiagnosisId;
    public Diagnosis Diagnosis;
    public int DoctorId;
    public Doctor Doctor;
    public int PatientId;
    public Patient Patient;
    public String Type;
    public int AssistantId;
    public Assistant Assistant;
    public String СabinetNum;
    public Date DateTime;

}
