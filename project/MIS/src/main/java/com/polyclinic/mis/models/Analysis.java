package com.polyclinic.mis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private int PatientId;
    //public Patient Patient;
    private String Type;
    private String Description;
    private int AssistantId;

    protected Analysis() {}

    public Analysis(int PatientId, String Type, String Description, int AssistantId) {
        this.PatientId = PatientId;
        this.Type = Type;
        this.Description = Description;
        this.AssistantId = AssistantId;
    }


    public Long getId() {
        return Id;
    }

    public int getPatientId() {
        return PatientId;
    }

    public String getType() {
        return Type;
    }

    public String getDescription() {
        return Description;
    }

    public int getAssistantId() {
        return AssistantId;
    }
    //public Assistant Assistant;
}
