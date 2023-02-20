package com.polyclinic.mis.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
@Entity
public class Assistant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String FirstName;
    private String LastName;
    private String MiddleName;
    private Date BirthDate;
    private String PolyclinicUserID;
    //public PolyclinicUser PolyclinicUser;
    @OneToMany
    private List<Analysis> Analyses;
    public String ReturnDateForDisplay()
    {
            return this.BirthDate.toString();
    }
    public String ReturnFIOAndBirthDate()
    {
            return LastName + " " + FirstName + " " + MiddleName + " " + this.BirthDate.toString();
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
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

    public List<Analysis> getAnalyses() {
        return Analyses;
    }

    public void setAnalyses(List<Analysis> analyses) {
        Analyses = analyses;
    }
}
