package com.polyclinic.mis.models;

import jakarta.persistence.*;

import java.sql.Date;
@Entity
public class DoctorAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Patient patient;
    private String cabinetId;
    private Date dateTime;
    private String status;
    @ManyToOne
    private Doctor doctor;

    public DoctorAppointment() {
    }

    public DoctorAppointment(Patient patient, String cabinetId, Date dateTime, String status, Doctor doctor) {
        this.patient = patient;
        this.cabinetId = cabinetId;
        this.dateTime = dateTime;
        this.status = status;
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "DoctorAppointment{" +
                "id=" + id +
                ", patient=" + patient +
                ", cabinetId='" + cabinetId + '\'' +
                ", dateTime=" + dateTime +
                ", status='" + status + '\'' +
                ", doctor=" + doctor +
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

    public String getCabinetId() {
        return cabinetId;
    }

    public void setCabinetId(String cabinetId) {
        this.cabinetId = cabinetId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
