package com.polyclinic.mis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int patientId;
    //public Patient Patient;
    private String type;
    private String description;
    private int assistantId;

    protected Analysis() {}

    public Analysis(int PatientId, String Type, String Description, int AssistantId) {
        this.patientId = PatientId;
        this.type = Type;
        this.description = Description;
        this.assistantId = AssistantId;
    }


    public Long getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getAssistantId() {
        return assistantId;
    }
    //public Assistant Assistant;
}
