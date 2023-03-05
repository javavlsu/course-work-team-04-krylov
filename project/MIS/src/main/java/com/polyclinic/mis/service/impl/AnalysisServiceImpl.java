package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.AnalysisRepository;
import com.polyclinic.mis.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private AnalysisRepository analysisRepository;
    @Override
    public Analysis add(Analysis analysis) {
        return analysisRepository.saveAndFlush(analysis);
    }
    @Override
    public Optional<Analysis> getById(Long id){
        return analysisRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        analysisRepository.deleteById(id);
    }

    @Override
    public Analysis edit(Analysis analysis) {
        return analysisRepository.saveAndFlush(analysis);
    }

    @Override
    public List<Analysis> getAll() {
        return analysisRepository.findAll();
    }
}
