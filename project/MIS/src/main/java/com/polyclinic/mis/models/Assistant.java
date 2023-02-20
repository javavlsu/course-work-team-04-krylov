package com.polyclinic.mis.models;

import java.sql.Date;
import java.util.List;

public class Assistant {
    public long Id;
    public String FirstName;
    public String LastName;
    public String MiddleName;
    public Date BirthDate;
    public String PolyclinicUserID;
    //public PolyclinicUser PolyclinicUser;
    public List<Analysis> Analyses;
    public String ReturnDateForDisplay()
    {
            return this.BirthDate.toString();
    }
    public String ReturnFIOAndBirthDate()
    {
            return LastName + " " + FirstName + " " + MiddleName + " " + this.BirthDate.toString();
    }

}
