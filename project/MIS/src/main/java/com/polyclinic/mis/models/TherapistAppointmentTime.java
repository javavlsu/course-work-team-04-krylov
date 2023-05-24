package com.polyclinic.mis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
public class TherapistAppointmentTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Doctor doctor;

    private String dayOfTheWeek;

    private Time time;

    @Transient
    private String timeString;
}
