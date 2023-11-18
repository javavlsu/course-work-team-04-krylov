package com.polyclinic.mis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Осмотр
 */
@Entity
@Data
@NoArgsConstructor
public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @NotNull(message = "Поле Пациент не может быть пустым")
    private Patient patient;
    @NotNull(message = "Поле Жалобы не может быть пустым")
    private String complaint;
    private String recipe;
    @ManyToOne
    @NotNull(message = "Поле Диагноз не может быть пустым")
    private Diagnosis diagnosis;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Поле Дата не может быть пустым")
    private LocalDateTime date;
    @NotNull(message = "Поле Тип не может быть пустым")
    private String type;
    @ManyToOne
    @NotNull(message = "Поле Врач не может быть пустым")
    private Doctor doctor;

    public Inspection(Patient patient, String complaint, String recipe, Diagnosis diagnosis, LocalDateTime date, String type, Doctor doctor) {
        this.patient = patient;
        this.complaint = complaint;
        this.recipe = recipe;
        this.diagnosis = diagnosis;
        this.date = date;
        this.type = type;
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "id=" + id +
                ", patient=" + patient +
                ", complaint='" + complaint + '\'' +
                ", recipe='" + recipe + '\'' +
                ", diagnosis=" + diagnosis +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", doctor=" + doctor +
                '}';
    }
}
