package com.polyclinic.mis.models;

public class Diagnosis {
    public String Id;
    public String Description;
    public String ReturnIdAndDescription()
    {
            return Id + " " + Description;
    }
}
