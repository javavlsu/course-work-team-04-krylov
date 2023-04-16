package com.polyclinic.mis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
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
    private Timestamp dateOfTaking;
    private String whatToResearch;
    private String schedule;

    public DoctorReferral(Diagnosis diagnosis, Doctor doctorInitial, Patient patient, Doctor doctorTarget, String cabinetNum, String status, Timestamp dateOfTaking, String whatToResearch, String schedule) {
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
}
