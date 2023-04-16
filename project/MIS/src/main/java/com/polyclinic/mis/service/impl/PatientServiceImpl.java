package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.PatientRepository;
import com.polyclinic.mis.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public Patient add(Patient patient) {
        if (patient.getPolisEndDate()!=null&&patient.getPolisEndDateString()!="")
        {
            patient.setPolisEndDate(Date.valueOf(patient.getPolisEndDateString()));
        }
        return patientRepository.saveAndFlush(patient);
    }
    @Override
    public Optional<Patient> getById(Long id){
        return patientRepository.findById(id);
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
