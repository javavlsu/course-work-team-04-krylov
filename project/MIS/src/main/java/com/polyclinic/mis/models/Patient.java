package com.polyclinic.mis.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

/**
 * Пациент
 */
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    //public PolyclinicUser PolyclinicUser
    private int polisID;
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

    public Patient() {
    }

    public Patient(String firstName, String lastName, String middleName, Date birthDate, int polisID, String poilsCompany, int snilsNumber, String homeAddress, String workPlace, String workPosition, Date polisEndDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.polisID = polisID;
        this.poilsCompany = poilsCompany;
        this.polisEndDate = polisEndDate;
        this.snilsNumber = snilsNumber;
        this.homeAddress = homeAddress;
        this.workPlace = workPlace;
        this.workPosition = workPosition;
    }
    public Patient(String firstName, String lastName, String middleName, Date birthDate, int polisID, String poilsCompany, int snilsNumber, String homeAddress, String workPlace, String workPosition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.polisID = polisID;
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
                ", polisID=" + polisID +
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

    public int getPolisID() {
        return polisID;
    }

    public void setPolisID(int polisID) {
        this.polisID = polisID;
    }

    public String getPoilsCompany() {
        return poilsCompany;
    }

    public void setPoilsCompany(String poilsCompany) {
        this.poilsCompany = poilsCompany;
    }

    public Date getPolisEndDate() {
        return polisEndDate;
    }

    public void setPolisEndDate(Date polisEndDate) {
        this.polisEndDate = polisEndDate;
    }

    public int getSnilsNumber() {
        return snilsNumber;
    }

    public void setSnilsNumber(int snilsNumber) {
        this.snilsNumber = snilsNumber;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

//    public List<Analysis> getAnalyses() {
//        return Analyses;
//    }
//
//    public void setAnalyses(List<Analysis> analyses) {
//        Analyses = analyses;
//    }
//
//    public List<Examination> getExaminations() {
//        return Examinations;
//    }
//
//    public void setExaminations(List<Examination> examinations) {
//        Examinations = examinations;
//    }
//
//    public List<Inspection> getInspections() {
//        return Inspections;
//    }
//
//    public void setInspections(List<Inspection> inspections) {
//        Inspections = inspections;
//    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
}
