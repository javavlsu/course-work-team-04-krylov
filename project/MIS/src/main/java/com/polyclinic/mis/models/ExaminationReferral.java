package com.polyclinic.mis.models;

import jakarta.persistence.*;

import java.sql.Date;
@Entity
public class ExaminationReferral {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String DiagnosisId;
    @ManyToOne
    private Diagnosis Diagnosis;
    private int DoctorId;
    @ManyToOne
    private Doctor Doctor;
    private int PatientId;
    @ManyToOne
    private Patient Patient;
    private String Type;
    private int FunctionalDiagnosticsDoctorId;
    @ManyToOne
    private FunctionalDiagnosticsDoctor FunctionalDiagnosticsDoctor;
    private String СabinetNum;
    private Date DateTime;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDiagnosisId() {
        return DiagnosisId;
    }

    public void setDiagnosisId(String diagnosisId) {
        DiagnosisId = diagnosisId;
    }

    public com.polyclinic.mis.models.Diagnosis getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(com.polyclinic.mis.models.Diagnosis diagnosis) {
        Diagnosis = diagnosis;
    }

    public int getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(int doctorId) {
        DoctorId = doctorId;
    }

    public com.polyclinic.mis.models.Doctor getDoctor() {
        return Doctor;
    }

    public void setDoctor(com.polyclinic.mis.models.Doctor doctor) {
        Doctor = doctor;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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

    public String getСabinetNum() {
        return СabinetNum;
    }

    public void setСabinetNum(String сabinetNum) {
        СabinetNum = сabinetNum;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }
}
