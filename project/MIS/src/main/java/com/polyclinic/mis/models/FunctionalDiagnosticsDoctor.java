package com.polyclinic.mis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

/**
 * Врач функциональной диагностики
 */
@Entity
@Data
@NoArgsConstructor
public class FunctionalDiagnosticsDoctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;

    @OneToOne
    private PolyclinicUser user;
    //public PolyclinicUser PolyclinicUser;
//    @OneToMany
//    private List<Examination> examinations;

    public FunctionalDiagnosticsDoctor(String firstName, String lastName, String middleName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "FunctionalDiagnosticsDoctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", user=" + user +
                '}';
    }

    public String ReturnDateForDisplay()
    {
            return this.birthDate.toString();
    }
    public String ReturnFIOAndBirthDate()
    {
            return lastName + " " + firstName + " " + middleName + " " + this.birthDate.toString();
    }

}
