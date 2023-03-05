package com.polyclinic.mis.service;

import com.polyclinic.mis.models.ExaminationReferral;
import com.polyclinic.mis.models.Receptionist;

import java.util.List;
import java.util.Optional;

public interface ExaminationReferralService {
    ExaminationReferral add(ExaminationReferral examinationReferral);
    Optional<ExaminationReferral> getById(Long id);
    void delete(Long id);
    ExaminationReferral edit(ExaminationReferral examinationReferral);
    List<ExaminationReferral> getAll();
}
