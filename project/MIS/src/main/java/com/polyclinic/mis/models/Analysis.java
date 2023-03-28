package com.polyclinic.mis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

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
    private Patient patient;
    private String type;
    private String report;
    @ManyToOne
    private Assistant assistant;
    private Date date;

    public Analysis(Patient patient, String type, String report, Assistant assistant, Date date) {
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
