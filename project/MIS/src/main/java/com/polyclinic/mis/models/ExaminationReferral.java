package com.polyclinic.mis.models;

import jakarta.persistence.*;

import java.sql.Date;

/**
 * Направление на обследование
 */
@Entity
public class ExaminationReferral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Diagnosis diagnosis;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    private String type;
    @ManyToOne
    private FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor;
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


    public com.polyclinic.mis.models.Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(com.polyclinic.mis.models.Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }


    public com.polyclinic.mis.models.Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(com.polyclinic.mis.models.Doctor doctor) {
        this.doctor = doctor;
    }


    public com.polyclinic.mis.models.Patient getPatient() {
        return patient;
    }

    public void setPatient(com.polyclinic.mis.models.Patient patient) {
        this.patient = patient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public com.polyclinic.mis.models.FunctionalDiagnosticsDoctor getFunctionalDiagnosticsDoctor() {
        return functionalDiagnosticsDoctor;
    }

    public void setFunctionalDiagnosticsDoctor(com.polyclinic.mis.models.FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor) {
        this.functionalDiagnosticsDoctor = functionalDiagnosticsDoctor;
    }

    public String getСabinetNum() {
        return cabinetNum;
    }

    public void setСabinetNum(String cabinetNum) {
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
