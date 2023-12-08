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
public class DoctorReferral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @NotNull(message = "Поле Диагноз не может быть пустым")
    private Diagnosis diagnosis;
    @ManyToOne
    @NotNull(message = "Поле Врач выписавший направление не может быть пустым")
    private Doctor doctorInitial;
    @ManyToOne
    @NotNull(message = "Поле Пациент не может быть пустым")
    private Patient patient;
    @ManyToOne
    @NotNull(message = "Поле Врач к кому выписано направление не может быть пустым")
    private Doctor doctorTarget;
    //todo Удалить поле. Номер кабинета брать из врача
    private String cabinetNum;
    private String status;

//todo Заменить на date или добавить во все направления дату выписки и дату проведения. Сортировать по дате выписки. Показывать дату выписки
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateOfTaking;
    @NotNull(message = "Поле Что обследовать не может быть пустым")
    private String whatToResearch;

    //todo Удалить поле. Брать расписание от кабинета врача
    private String schedule;

    public DoctorReferral(Diagnosis diagnosis, Doctor doctorInitial, Patient patient, Doctor doctorTarget, String cabinetNum, String status, LocalDateTime dateOfTaking, String whatToResearch, String schedule) {
        this.diagnosis = diagnosis;
        this.doctorInitial = doctorInitial;
        this.patient = patient;
        this.doctorTarget = doctorTarget;
        this.cabinetNum = cabinetNum;
        this.status = status;
        this.dateOfTaking = dateOfTaking;
        this.whatToResearch = whatToResearch;
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "DoctorReferral{" +
                "id=" + id +
                ", diagnosis=" + diagnosis +
                ", doctorInitial=" + doctorInitial +
                ", patient=" + patient +
                ", doctorTarget=" + doctorTarget +
                ", cabinetNum='" + cabinetNum + '\'' +
                ", status='" + status + '\'' +
                ", dateOfTaking=" + dateOfTaking +
                ", whatToResearch='" + whatToResearch + '\'' +
                ", schedule='" + schedule + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
