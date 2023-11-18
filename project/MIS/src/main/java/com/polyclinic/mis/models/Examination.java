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

@Entity
@Data
@NoArgsConstructor
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Поле Тип не может быть пустым")
    private String type;
    @NotNull(message = "Поле Заключение не может быть пустым")
    private String report;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Поле Дата проведения не может быть пустым")
    private LocalDateTime date;
    @ManyToOne
    @NotNull(message = "Поле Врач функциональной диагностики не может быть пустым")
    private FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor;
    @ManyToOne
    @NotNull(message = "Поле Пациент не может быть пустым")
    private Patient patient;

    @ManyToOne
    private ExaminationReferral examinationReferral;

    public Examination(String type, String report, LocalDateTime date, FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor, Patient patient) {
        this.type = type;
        this.report = report;
        this.date = date;
        this.functionalDiagnosticsDoctor = functionalDiagnosticsDoctor;
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Examination{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", report='" + report + '\'' +
                ", date=" + date +
                ", functionalDiagnosticsDoctor=" + functionalDiagnosticsDoctor +
                ", patient=" + patient +
                '}';
    }

}
