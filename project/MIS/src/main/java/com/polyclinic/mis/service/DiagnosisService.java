package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Diagnosis;

import java.util.List;

public interface DiagnosisService {
    Diagnosis add(Diagnosis diagnosis);
    void delete(String id);
    List<Diagnosis> findByDescriptionContaining(String description);
    Diagnosis edit(Diagnosis diagnosis);
    List<Diagnosis> getAll();
}
