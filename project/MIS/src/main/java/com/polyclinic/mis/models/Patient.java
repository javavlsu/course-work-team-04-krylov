package com.polyclinic.mis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

/**
 * Пациент
 */
@Entity
@Data
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    //public PolyclinicUser PolyclinicUser
    private int polisId;
    private String poilsCompany;
    private Date polisEndDate;
    private int snilsNumber;
    private String homeAddress;
    private String workPlace;
    private String workPosition;
//    @OneToMany
//    private List<Analysis> Analyses;
//    @OneToMany
//    private List<Examination> Examinations;
//    @OneToMany
//    private List<Inspection> Inspections;


    public Patient(String firstName, String lastName, String middleName, Date birthDate, int polisId, String poilsCompany, int snilsNumber, String homeAddress, String workPlace, String workPosition, Date polisEndDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.polisId = polisId;
        this.poilsCompany = poilsCompany;
        this.polisEndDate = polisEndDate;
        this.snilsNumber = snilsNumber;
        this.homeAddress = homeAddress;
        this.workPlace = workPlace;
        this.workPosition = workPosition;
    }
    public Patient(String firstName, String lastName, String middleName, Date birthDate, int polisId, String poilsCompany, int snilsNumber, String homeAddress, String workPlace, String workPosition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.polisId = polisId;
        this.poilsCompany = poilsCompany;
        this.snilsNumber = snilsNumber;
        this.homeAddress = homeAddress;
        this.workPlace = workPlace;
        this.workPosition = workPosition;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", polisID=" + polisId +
                ", poilsCompany='" + poilsCompany + '\'' +
                ", polisEndDate=" + polisEndDate +
                ", snilsNumber=" + snilsNumber +
                ", homeAddress='" + homeAddress + '\'' +
                ", workPlace='" + workPlace + '\'' +
                ", workPosition='" + workPosition + '\'' +
                '}';
    }

    public String ReturnBirthDateForDisplay()
    {
            return this.birthDate.toString();
    }
    public String ReturnPolisEndDateForDisplay()
    {
            return this.polisEndDate.toString();
    }
    public String ReturnFIO()
    {
            return lastName + " " + firstName + " " + middleName;
    }
    public String ReturnFIOAndBirthDate()
    {
            return lastName + " " + firstName + " " + middleName + " " + this.birthDate.toString();
    }
}
