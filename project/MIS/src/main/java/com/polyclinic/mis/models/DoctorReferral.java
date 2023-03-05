package com.polyclinic.mis.models;

import jakarta.persistence.*;

import java.sql.Date;
@Entity
public class DoctorReferral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Diagnosis diagnosis;
    @ManyToOne
    private Doctor doctorInitial;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctorTarget;
    private String cabinetNum;
    private String status;
    private Date dateOfTaking;
    private String whatToResearch;
    private String schedule;

    public DoctorReferral() {
    }

    public DoctorReferral(Diagnosis diagnosis, Doctor doctorInitial, Patient patient, Doctor doctorTarget, String cabinetNum, String status, Date dateOfTaking, String whatToResearch, String schedule) {
        this.diagnosis = diagnosis;
        this.doctorInitial = doctorInitial;
        this.patient = patient;
        this.doctorTarget = doctorTarget;
        this.cabinetNum = cabinetNum;
        this.status = status;
        this.dateOfTaking = dateOfTaking;
        this.whatToResearch = whatToResearch;
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "DoctorReferral{" +
                "id=" + id +
                ", diagnosis=" + diagnosis +
                ", doctorInitial=" + doctorInitial +
                ", patient=" + patient +
                ", doctorTarget=" + doctorTarget +
                ", cabinetNum='" + cabinetNum + '\'' +
                ", status='" + status + '\'' +
                ", dateOfTaking=" + dateOfTaking +
                ", whatToResearch='" + whatToResearch + '\'' +
                ", schedule='" + schedule + '\'' +
                '}';
    }

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


    public Doctor getDoctorInitial() {
        return doctorInitial;
    }

    public void setDoctorInitial(Doctor doctorInitial) {
        this.doctorInitial = doctorInitial;
    }


    public com.polyclinic.mis.models.Patient getPatient() {
        return patient;
    }

    public void setPatient(com.polyclinic.mis.models.Patient patient) {
        this.patient = patient;
    }


    public Doctor getDoctorTarget() {
        return doctorTarget;
    }

    public void setDoctorTarget(Doctor doctorTarget) {
        this.doctorTarget = doctorTarget;
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
