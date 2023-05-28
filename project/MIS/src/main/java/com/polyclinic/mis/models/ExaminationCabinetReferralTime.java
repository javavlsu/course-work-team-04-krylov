package com.polyclinic.mis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
public class ExaminationCabinetReferralTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @NotNull(message = "Поле Кабинет не может быть пустым")
    private ExaminationCabinet examinationCabinet;

    private Time time;

    @Transient
    private String timeString;
}
