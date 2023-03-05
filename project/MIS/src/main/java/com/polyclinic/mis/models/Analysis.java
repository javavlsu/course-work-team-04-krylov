package com.polyclinic.mis.models;

import jakarta.persistence.*;

import java.sql.Date;

/**
 * Анализ
 */
@Entity
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //private int patientId;
    @ManyToOne
    private Patient patient;
    private String type;
    private String report;
    @ManyToOne
    private Assistant assistant;
    private Date date;

    public Analysis() {
    }

    public Analysis(Patient patient, String type, String report, Assistant assistant, Date date) {
        this.patient = patient;
        this.type = type;
        this.report = report;
        this.assistant = assistant;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Analysis{" +
                "id=" + id +
                ", patient=" + patient +
                ", type='" + type + '\'' +
                ", report='" + report + '\'' +
                ", assistant=" + assistant +
                ", date=" + date +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String description) {
        this.report = description;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
