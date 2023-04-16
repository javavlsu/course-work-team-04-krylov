package com.polyclinic.mis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class DoctorAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @ManyToOne
//    private Patient patient;
//    private String cabinetId;
    private Timestamp dateTime;
    private String status;
//    @ManyToOne
//    private Doctor doctor;
    @OneToOne
    private DoctorReferral doctorReferral;

    public DoctorAppointment(Timestamp dateTime, String status,DoctorReferral doctorReferral) {
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

