package com.polyclinic.mis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Entity
@Data
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    //public PolyclinicUser PolyclinicUser;
    private String speciality;
    private String category;
    private String degree;
    @OneToOne
    private PolyclinicUser user;

    public Doctor(String firstName, String lastName, String middleName, Date birthDate, String speciality, String category, String degree) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.speciality = speciality;
        this.category = category;
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", speciality='" + speciality + '\'' +
                ", category='" + category + '\'' +
                ", degree='" + degree + '\'' +
                ", user=" + user +
                '}';
    }

    public String ReturnDateForDisplay()
    {
            return this.birthDate.toString();
    }
    public String ReturnFIO()
    {
            return lastName + " " + firstName + " " + middleName;
    }
    public String ReturnFIOAndSpeciality()
    {
            return lastName + " " + firstName + " " + middleName + " (" + speciality + ")";
    }
}
