package com.polyclinic.mis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Мед регистратор
 */
@Entity
@Data
@NoArgsConstructor
public class Receptionist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Поле Имя не может быть пустым")
    private String firstName;
    @NotNull(message = "Поле Фамилия не может быть пустым")
    private String lastName;
    private String middleName;
    @NotNull(message = "Поле Дата рождения не может быть пустым")
    private Date birthDate;
    @OneToOne
    private PolyclinicUser user;
    //public PolyclinicUser PolyclinicUser;


    public Receptionist(String firstName, String lastName, String middleName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Receptionist{" +
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
    public String ReturnFIO()
    {
            return lastName + " " + firstName + " " + middleName;
    }
    public String ReturnFIOAndBirthDate()
    {
//            return lastName + " " + firstName + " " + middleName + " " + this.birthDate.toString();
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return lastName + " " + firstName + " " + middleName + " " + simpleDateFormat.format(this.birthDate);
    }
}
