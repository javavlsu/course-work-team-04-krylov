package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Inspection;
import com.polyclinic.mis.models.Patient;

import java.util.List;

public interface PatientService {
    Patient add(Patient patient);
    void delete(Long id);
    Patient edit(Patient patient);
    List<Patient> getAll();

}
