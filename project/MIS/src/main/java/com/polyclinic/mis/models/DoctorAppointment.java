package com.polyclinic.mis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class DoctorAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @ManyToOne
//    private Diagnosis diagnosis;

    @ManyToOne
    @NotNull(message = "Выберите врача")
    private Doctor doctor;


    @ManyToOne
    @NotNull (message = "Поле Пациент не может быть пустым")
    private Patient patient;
//    private String cabinetNum;
    private String status;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
//    private LocalDateTime date;

    @NotNull (message = "Выберите дату записи")
    private Date date;

    @NotNull (message = "Выберите время записи")
    private Time time;

}
