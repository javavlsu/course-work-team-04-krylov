package com.polyclinic.mis.models;

import jakarta.persistence.*;
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
    private String type;
    private String report;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    @ManyToOne
    private FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor;
    @ManyToOne
    private Patient patient;

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
