package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.PatientRepository;
import com.polyclinic.mis.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public Patient add(Patient patient) {
        return patientRepository.saveAndFlush(patient);
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient edit(Patient patient) {
        return patientRepository.saveAndFlush(patient);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }
}
