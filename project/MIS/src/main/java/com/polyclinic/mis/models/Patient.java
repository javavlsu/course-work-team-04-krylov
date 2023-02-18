package com.polyclinic.mis.models;

import java.sql.Date;
import java.util.List;

public class Patient {
    public int Id;
    public String FirstName;
    public String LastName;
    public String MiddleName;
    public Date BirthDate;
    public String PolyclinicUserID;
    //public PolyclinicUser PolyclinicUser
    public int PolisID;
    public String PoilsCompany;
    public Date PolisEndDate;
    public int SnilsNumber;
    public String WorkPlace;
    public List<Analysis> Analyses;
    //public List<Examination> Examinations;
    //public List<Inspection> Inspections;
    public String ReturnBirthDateForDisplay()
    {
            return this.BirthDate.toString();
    }
    public String ReturnPolisEndDateForDisplay()
    {
            return this.PolisEndDate.toString();
    }
    public String ReturnFIO()
    {
            return LastName + " " + FirstName + " " + MiddleName;
    }
    public String ReturnFIOAndBirthDate()
    {
            return LastName + " " + FirstName + " " + MiddleName + " " + this.BirthDate.toString();
    }
}
