package com.polyclinic.mis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * Направление на обследование
 */
@Entity
@Data
@NoArgsConstructor
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


    public ExaminationReferral(Diagnosis diagnosis, Doctor doctor, Patient patient, String type, FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor, String cabinetNum, String status, Date dateOfTaking, String whatToResearch, String schedule) {
        this.diagnosis = diagnosis;
        this.doctor = doctor;
        this.patient = patient;
        this.type = type;
        this.functionalDiagnosticsDoctor = functionalDiagnosticsDoctor;
        this.cabinetNum = cabinetNum;
        this.status = status;
        this.dateOfTaking = dateOfTaking;
        this.whatToResearch = whatToResearch;
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "ExaminationReferral{" +
                "id=" + id +
                ", diagnosis=" + diagnosis +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", type='" + type + '\'' +
                ", functionalDiagnosticsDoctor=" + functionalDiagnosticsDoctor +
                ", cabinetNum='" + cabinetNum + '\'' +
                ", status='" + status + '\'' +
                ", dateOfTaking=" + dateOfTaking +
                ", whatToResearch='" + whatToResearch + '\'' +
                ", schedule='" + schedule + '\'' +
                '}';
    }
}
