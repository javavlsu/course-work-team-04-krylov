package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.ExaminationReferral;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.ExaminationReferralRepository;
import com.polyclinic.mis.repository.ExaminationRepository;
import com.polyclinic.mis.service.ExaminationReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExaminationReferralServiceImpl implements ExaminationReferralService {
    @Autowired
    private ExaminationReferralRepository examinationReferralRepository;
    @Override
    public ExaminationReferral add(ExaminationReferral examinationReferral) {
        return examinationReferralRepository.saveAndFlush(examinationReferral);
    }
    @Override
    public Optional<ExaminationReferral> getById(Long id){
        return examinationReferralRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        examinationReferralRepository.deleteById(id);
    }

    @Override
    public ExaminationReferral edit(ExaminationReferral examinationReferral) {
        return examinationReferralRepository.saveAndFlush(examinationReferral);
    }

    @Override
    public List<ExaminationReferral> getAll() {
        return examinationReferralRepository.findAll();
    }
}
