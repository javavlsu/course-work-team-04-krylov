package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient add(Patient patient);
    void delete(Long id);

    Optional<Patient> getById(Long id);
    Patient edit(Patient patient);
    List<Patient> getAll();

}
