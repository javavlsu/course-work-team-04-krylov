package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.repository.ExaminationRepository;
import com.polyclinic.mis.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    private ExaminationRepository examinationRepository;
    @Override
    public Examination add(Examination examination) {
        return examinationRepository.saveAndFlush(examination);
    }

    @Override
    public void delete(Long id) {
        examinationRepository.deleteById(id);
    }

    @Override
    public Examination edit(Examination examination) {
        return examinationRepository.saveAndFlush(examination);
    }

    @Override
    public List<Examination> getAll() {
        return examinationRepository.findAll();
    }
}
