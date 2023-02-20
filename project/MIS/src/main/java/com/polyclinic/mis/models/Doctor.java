package com.polyclinic.mis.models;

import java.sql.Date;

public class Doctor {
    public long Id;
    public String FirstName;
    public String LastName;
    public String MiddleName;
    public Date BirthDate;
    public String PolyclinicUserID;
    //public PolyclinicUser PolyclinicUser;
    public String Speciality;
    public String Category;
    public String Degree;
    public String ReturnDateForDisplay()
    {
            return this.BirthDate.toString();
    }
    public String ReturnFIO()
    {
            return LastName + " " + FirstName + " " + MiddleName;
    }
    public String ReturnFIOAndSpeciality()
    {
            return LastName + " " + FirstName + " " + MiddleName + " (" + Speciality + ")";
    }
}
