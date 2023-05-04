package com.polyclinic.mis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.thymeleaf.spring6.processor.SpringOptionFieldTagProcessor;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Направление на анализ
 */
@Entity
@Data
@NoArgsConstructor
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateOfTaking;
    private String whatToResearch;
    private String schedule;

    public AnalysisReferral(Diagnosis diagnosis, Doctor doctor, Patient patient, String laboratory, Assistant assistant, String cabinetNum, String status, LocalDateTime dateOfTaking, String whatToResearch, String schedule) {
        this.diagnosis = diagnosis;
        this.doctor = doctor;
        this.patient = patient;
        this.laboratory = laboratory;
        this.assistant = assistant;
        this.cabinetNum = cabinetNum;
        this.status = status;
        this.dateOfTaking = dateOfTaking;
        this.whatToResearch = whatToResearch;
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "AnalysisReferral{" +
                "id=" + id +
                ", diagnosis=" + diagnosis +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", laboratory='" + laboratory + '\'' +
                ", assistant=" + assistant +
                ", cabinetNum='" + cabinetNum + '\'' +
                ", status='" + status + '\'' +
                ", dateOfTaking=" + dateOfTaking +
                ", whatToResearch='" + whatToResearch + '\'' +
                ", schedule='" + schedule + '\'' +
                '}';
    }
}
