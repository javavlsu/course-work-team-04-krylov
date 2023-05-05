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
public class DoctorAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;
    private String status;
    @OneToOne
    private DoctorReferral doctorReferral;

    public DoctorAppointment(LocalDateTime dateTime, String status, DoctorReferral doctorReferral) {
        //this.patient = patient;
        //this.cabinetId = cabinetId;
        this.dateTime = dateTime;
        this.status = status;
        // this.doctor = doctor;
        this.doctorReferral = doctorReferral;
    }

    @Override
    public String toString() {
        return "DoctorAppointment{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", status='" + status + '\'' +
                ", doctorReferral=" + doctorReferral +
                '}';
    }

}

