package com.polyclinic.mis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

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
    private Patient patient;
    private String complaint;
    private String recipe;
    @ManyToOne
    private Diagnosis diagnosis;
    private Date date;
    private String type;
    @ManyToOne
    private Doctor doctor;

    public Inspection(Patient patient, String complaint, String recipe, Diagnosis diagnosis, Date date, String type, Doctor doctor) {
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
