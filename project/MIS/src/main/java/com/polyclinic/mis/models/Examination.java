package com.polyclinic.mis.models;

import jakarta.persistence.*;

@Entity
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String Type;
    private String Description;
    private int FunctionalDiagnosticsDoctorId;
    @ManyToOne
    private FunctionalDiagnosticsDoctor FunctionalDiagnosticsDoctor;
    private int PatientId;
    @ManyToOne
    private Patient Patient;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getFunctionalDiagnosticsDoctorId() {
        return FunctionalDiagnosticsDoctorId;
    }

    public void setFunctionalDiagnosticsDoctorId(int functionalDiagnosticsDoctorId) {
        FunctionalDiagnosticsDoctorId = functionalDiagnosticsDoctorId;
    }

    public com.polyclinic.mis.models.FunctionalDiagnosticsDoctor getFunctionalDiagnosticsDoctor() {
        return FunctionalDiagnosticsDoctor;
    }

    public void setFunctionalDiagnosticsDoctor(com.polyclinic.mis.models.FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor) {
        FunctionalDiagnosticsDoctor = functionalDiagnosticsDoctor;
    }

    public int getPatientId() {
        return PatientId;
    }

    public void setPatientId(int patientId) {
        PatientId = patientId;
    }

    public com.polyclinic.mis.models.Patient getPatient() {
        return Patient;
    }

    public void setPatient(com.polyclinic.mis.models.Patient patient) {
        Patient = patient;
    }
}
