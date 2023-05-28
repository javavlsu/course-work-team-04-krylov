package com.polyclinic.mis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    @NotNull(message = "Поле Диагноз не может быть пустым")
    private Diagnosis diagnosis;
    @ManyToOne
    @NotNull(message = "Поле Врач не может быть пустым")
    private Doctor doctor;
    @ManyToOne
    @NotNull(message = "Поле Пациент не может быть пустым")
    private Patient patient;
//    private String type;

    @ManyToOne
    @NotNull(message = "Поле Кабинет не может быть пустым")
    private ExaminationCabinet cabinet;


//    @ManyToOne
//    private FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor;
//    private String cabinetNum;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateOfTaking;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateOfIssue;
    @NotNull(message = "Поле Что обследовать не может быть пустым")
    private String whatToResearch;

    @NotNull (message = "Поле Дата записи не может быть пустым")
    private Date date;

    @NotNull (message = "Поле Время записи не может быть пустым")
    private Time time;
//    private String schedule;


    public ExaminationReferral(Diagnosis diagnosis, Doctor doctor, Patient patient, String status, LocalDateTime dateOfTaking, String whatToResearch) {
        this.diagnosis = diagnosis;
        this.doctor = doctor;
        this.patient = patient;
//        this.type = type;
//        this.functionalDiagnosticsDoctor = functionalDiagnosticsDoctor;
//        this.cabinetNum = cabinetNum;
        this.status = status;
        this.dateOfTaking = dateOfTaking;
        this.whatToResearch = whatToResearch;
//        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "ExaminationReferral{" +
                "id=" + id +
                ", diagnosis=" + diagnosis +
                ", doctor=" + doctor +
                ", patient=" + patient +
//                ", type='" + type + '\'' +
//                ", cabinetNum='" + cabinetNum + '\'' +
                ", status='" + status + '\'' +
                ", dateOfTaking=" + dateOfTaking +
                ", whatToResearch='" + whatToResearch + '\'' +
//                ", schedule='" + schedule + '\'' +
                '}';
    }
}
