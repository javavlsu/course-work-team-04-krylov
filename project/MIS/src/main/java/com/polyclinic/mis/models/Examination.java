package com.polyclinic.mis.models;

import jakarta.persistence.*;

@Entity
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String report;
    @ManyToOne
    private FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor;
    @ManyToOne
    private Patient patient;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }


    public com.polyclinic.mis.models.FunctionalDiagnosticsDoctor getFunctionalDiagnosticsDoctor() {
        return functionalDiagnosticsDoctor;
    }

    public void setFunctionalDiagnosticsDoctor(com.polyclinic.mis.models.FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor) {
        this.functionalDiagnosticsDoctor = functionalDiagnosticsDoctor;
    }


    public com.polyclinic.mis.models.Patient getPatient() {
        return patient;
    }

    public void setPatient(com.polyclinic.mis.models.Patient patient) {
        this.patient = patient;
    }
}
