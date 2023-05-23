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
//    private String type;

    @ManyToOne
    private ExaminationCabinet cabinet;


//    @ManyToOne
//    private FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor;
//    private String cabinetNum;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateOfTaking;
    private String whatToResearch;
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
