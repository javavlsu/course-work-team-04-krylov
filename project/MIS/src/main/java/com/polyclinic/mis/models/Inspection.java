package com.polyclinic.mis.models;

import jakarta.persistence.*;

import java.sql.Date;

/**
 * Осмотр
 */
@Entity

public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Patient patient;
    private String complaint;
    private String recipe;
    @ManyToOne
    private Diagnosis diagnosis;
    private Date date;
    private String type;
    @ManyToOne
    private Doctor doctor;

    public Inspection() {
    }

    public Inspection(Patient patient, String complaint, String recipe, Diagnosis diagnosis, Date date, String type, Doctor doctor) {
        this.patient = patient;
        this.complaint = complaint;
        this.recipe = recipe;
        this.diagnosis = diagnosis;
        this.date = date;
        this.type = type;
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "id=" + id +
                ", patient=" + patient +
                ", complaint='" + complaint + '\'' +
                ", recipe='" + recipe + '\'' +
                ", diagnosis=" + diagnosis +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", doctor=" + doctor +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        public com.polyclinic.mis.models.Patient getPatient() {
        return patient;
    }

    public void setPatient(com.polyclinic.mis.models.Patient patient) {
        this.patient = patient;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }


    public com.polyclinic.mis.models.Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(com.polyclinic.mis.models.Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public com.polyclinic.mis.models.Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(com.polyclinic.mis.models.Doctor doctor) {
        this.doctor = doctor;
    }
}
