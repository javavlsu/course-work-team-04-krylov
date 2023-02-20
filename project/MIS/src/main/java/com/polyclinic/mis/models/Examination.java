package com.polyclinic.mis.models;

public class Examination {
    public int Id;
    public String Type;
    public String Description;
    public int FunctionalDiagnosticsDoctorId;
    public FunctionalDiagnosticsDoctor FunctionalDiagnosticsDoctor;
    public int PatientId;
    public Patient Patient;

}
