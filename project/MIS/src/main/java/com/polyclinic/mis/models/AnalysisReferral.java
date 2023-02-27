package com.polyclinic.mis.models;

import jakarta.persistence.*;
import org.thymeleaf.spring6.processor.SpringOptionFieldTagProcessor;

import java.sql.Date;

/**
 * Направление на анализ
 */
@Entity
public class AnalysisReferral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Diagnosis diagnosis;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne

    private Patient patient;
    private String laboratory;
    @ManyToOne
    private Assistant assistant;
    private String cabinetNum;
    private String status;
    private Date dateOfTaking;
    private String whatToResearch;
    private String schedule;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }


    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(String type) {
        this.laboratory = type;
    }


    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public String getCabinetNum() {
        return cabinetNum;
    }

    public void setCabinetNum(String cabinetNum) {
        this.cabinetNum = cabinetNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateOfTaking() {
        return dateOfTaking;
    }

    public void setDateOfTaking(Date dateOfTaking) {
        this.dateOfTaking = dateOfTaking;
    }

    public String getWhatToResearch() {
        return whatToResearch;
    }

    public void setWhatToResearch(String whatToResearch) {
        this.whatToResearch = whatToResearch;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
