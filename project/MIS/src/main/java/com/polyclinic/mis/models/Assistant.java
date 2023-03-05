package com.polyclinic.mis.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

/**
 * Лаборант
 */
@Entity
public class Assistant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    //public PolyclinicUser PolyclinicUser;
    //@OneToMany
    //private List<Analysis> analyses;

    public Assistant() {
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

//    public List<Analysis> getAnalyses() {
//        return analyses;
//    }
//
//    public void setAnalyses(List<Analysis> analyses) {
//        this.analyses = analyses;
//    }
}
