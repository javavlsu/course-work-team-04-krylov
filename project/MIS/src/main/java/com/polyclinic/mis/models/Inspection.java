package com.polyclinic.mis.models;

import jakarta.persistence.*;

import java.sql.Date;
@Entity

public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private int PatientID;
    @ManyToOne
    private Patient Patient;
    private String Complaint;
    private String Recipe;
    private String DiagnosisId;
    @ManyToOne
    private Diagnosis Diagnosis;
    private Date Date;
    private String Type;
    private int DoctorId;
    @ManyToOne
    private Doctor Doctor;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int patientID) {
        PatientID = patientID;
    }

    public com.polyclinic.mis.models.Patient getPatient() {
        return Patient;
    }

    public void setPatient(com.polyclinic.mis.models.Patient patient) {
        Patient = patient;
    }

    public String getComplaint() {
        return Complaint;
    }

    public void setComplaint(String complaint) {
        Complaint = complaint;
    }

    public String getRecipe() {
        return Recipe;
    }

    public void setRecipe(String recipe) {
        Recipe = recipe;
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

    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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
}
