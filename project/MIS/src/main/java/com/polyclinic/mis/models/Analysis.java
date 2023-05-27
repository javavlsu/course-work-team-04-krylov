package com.polyclinic.mis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.flywaydb.core.api.logging.Log;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Анализ
 */
@Entity
@Data
@NoArgsConstructor
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //private int patientId;
    @ManyToOne
    @NotNull(message = "Поле Пациент не может быть пустым")
    private Patient patient;
    @NotNull(message = "Поле Тип не может быть пустым")
    private String type;
    @NotNull(message = "Поле Заключение не может быть пустым")
    private String report;
    @ManyToOne
    @NotNull(message = "Поле Лаборант не может быть пустым")
    private Assistant assistant;
    //    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Поле Дата не может быть пустым")
    private LocalDateTime date;

    @ManyToOne
    private AnalysisReferral analysisReferral;

    public Analysis(Patient patient, String type, String report, Assistant assistant, LocalDateTime date) {
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


}
