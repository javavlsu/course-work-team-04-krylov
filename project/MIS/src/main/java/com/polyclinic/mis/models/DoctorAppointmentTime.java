package com.polyclinic.mis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
public class DoctorAppointmentTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @NotNull(message = "Поле Врач не может быть пустым")
    private Doctor doctor;
    @NotNull(message = "Поле Время не может быть пустым")
    private Time time;

    @Transient
    private String timeString;
}
