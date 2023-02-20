package com.polyclinic.mis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;
@Entity
public class Receptionist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String FirstName;
    private String LastName;
    private String MiddleName;
    private Date BirthDate;
    private String PolyclinicUserID;
    //public PolyclinicUser PolyclinicUser;
    public String ReturnDateForDisplay()
    {
            return this.BirthDate.toString();
    }
    public String ReturnFIO()
    {
            return LastName + " " + FirstName + " " + MiddleName;
    }
    public String ReturnFIOAndBirthDate()
    {
            return LastName + " " + FirstName + " " + MiddleName + " " + this.BirthDate.toString();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public String getPolyclinicUserID() {
        return PolyclinicUserID;
    }

    public void setPolyclinicUserID(String polyclinicUserID) {
        PolyclinicUserID = polyclinicUserID;
    }
}
