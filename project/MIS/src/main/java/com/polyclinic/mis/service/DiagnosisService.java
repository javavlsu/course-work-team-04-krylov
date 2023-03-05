package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Diagnosis;
import com.polyclinic.mis.models.Receptionist;

import java.util.List;
import java.util.Optional;

public interface DiagnosisService {
    Diagnosis add(Diagnosis diagnosis);
    Optional<Diagnosis> getById(String id);
    void delete(String id);
    List<Diagnosis> findByDescriptionContaining(String description);
    Diagnosis edit(Diagnosis diagnosis);
    List<Diagnosis> getAll();
}
