package com.polyclinic.mis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class DoctorReferralAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;
    private String status;
    @OneToOne
    private DoctorReferral doctorReferral;

    public DoctorReferralAppointment(LocalDateTime dateTime, String status, DoctorReferral doctorReferral) {
        //this.patient = patient;
        //this.cabinetId = cabinetId;
        this.dateTime = dateTime;
        this.status = status;
        // this.doctor = doctor;
        this.doctorReferral = doctorReferral;
    }

    @Override
    public String toString() {
        return "DoctorReferralAppointment{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", status='" + status + '\'' +
                ", doctorReferral=" + doctorReferral +
                '}';
    }

}

