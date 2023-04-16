package com.polyclinic.mis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Лаборант
 */
@Entity
@Data
@NoArgsConstructor
public class Assistant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    @OneToOne
    private PolyclinicUser user;
    //@OneToMany
    //private List<Analysis> analyses;
    public Assistant(String firstName, String lastName, String middleName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Assistant{" +
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
//            return lastName + " " + firstName + " " + middleName + " " + this.birthDate.toString();
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return lastName + " " + firstName + " " + middleName + " " + simpleDateFormat.format(this.birthDate);
    }
}
