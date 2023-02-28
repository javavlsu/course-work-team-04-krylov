package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Diagnosis;
import com.polyclinic.mis.repository.DiagnosisRepository;
import com.polyclinic.mis.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiagnosisServiceImpl implements DiagnosisService {
    @Autowired
    private DiagnosisRepository diagnosisRepository;
    @Override
    public Diagnosis add(Diagnosis diagnosis) {
        return diagnosisRepository.saveAndFlush(diagnosis);
    }

    @Override
    public void delete(String id) {
    diagnosisRepository.deleteById(id);
    }

    @Override
    public List<Diagnosis> findByDescriptionContaining(String description) {
        return diagnosisRepository.findByDescriptionContaining(description);
    }

    @Override
    public Diagnosis edit(Diagnosis diagnosis) {
        return diagnosisRepository.saveAndFlush(diagnosis);
    }

    @Override
    public List<Diagnosis> getAll() {
        return diagnosisRepository.findAll();
    }
}
