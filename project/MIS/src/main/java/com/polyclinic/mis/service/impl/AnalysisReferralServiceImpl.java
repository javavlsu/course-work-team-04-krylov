package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.repository.AnalysisReferralRepository;
import com.polyclinic.mis.service.AnalysisReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AnalysisReferralServiceImpl implements AnalysisReferralService {
    @Autowired
    private AnalysisReferralRepository analysisReferralRepository;

    @Override
    public AnalysisReferral add(AnalysisReferral analysisReferral) {
        return analysisReferralRepository.saveAndFlush(analysisReferral);
    }
    @Override
    public Optional<AnalysisReferral> getById(Long id){
        return analysisReferralRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        analysisReferralRepository.deleteById(id);
    }

    @Override
    public AnalysisReferral edit(AnalysisReferral analysisReferral) {

        return analysisReferralRepository.saveAndFlush(analysisReferral);
    }

    @Override
    public List<AnalysisReferral> getAll() {
        return analysisReferralRepository.findAll();
    }
}
