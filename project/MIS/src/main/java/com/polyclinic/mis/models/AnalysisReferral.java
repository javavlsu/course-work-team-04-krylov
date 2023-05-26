package com.polyclinic.mis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Поле Диагноз не может быть пустым")
    private Diagnosis diagnosis;
    @ManyToOne
    @NotNull(message = "Поле Врач не может быть пустым")
    private Doctor doctor;
    @ManyToOne
    @NotNull(message = "Поле Пациент не может быть пустым")
    private Patient patient;
//    private String laboratory;
    @ManyToOne
    @NotNull(message = "Поле Кабинет не может быть пустым")
    private AnalysisCabinet cabinet;

//    @ManyToOne
//    private Assistant assistant;
//    private String cabinetNum;
    private String status;

    //todo Убрать. Переделать направления
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Поле Дата проведения не может быть пустым")
    private LocalDateTime dateOfTaking;
    @NotNull(message = "Поле Что исследовать не может быть пустым")
    private String whatToResearch;
//    private String schedule;

    public AnalysisReferral(Diagnosis diagnosis, Doctor doctor, Patient patient, String status, LocalDateTime dateOfTaking, String whatToResearch) {
        this.diagnosis = diagnosis;
        this.doctor = doctor;
        this.patient = patient;
//        this.laboratory = laboratory;
//        this.assistant = assistant;
//        this.cabinetNum = cabinetNum;
        this.status = status;
        this.dateOfTaking = dateOfTaking;
        this.whatToResearch = whatToResearch;
//        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "AnalysisReferral{" +
                "id=" + id +
                ", diagnosis=" + diagnosis +
                ", doctor=" + doctor +
                ", patient=" + patient +
//                ", laboratory='" + laboratory + '\'' +
//                ", cabinetNum='" + cabinetNum + '\'' +
                ", status='" + status + '\'' +
                ", dateOfTaking=" + dateOfTaking +
                ", whatToResearch='" + whatToResearch + '\'' +
//                ", schedule='" + schedule + '\'' +
                '}';
    }
}
