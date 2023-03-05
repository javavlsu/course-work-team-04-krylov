package com.polyclinic.mis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Diagnosis {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String description;

    public Diagnosis() {
    }

    public Diagnosis(String id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String ReturnIdAndDescription()
    {
            return id + " " + description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
