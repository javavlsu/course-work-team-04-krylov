package com.polyclinic.mis.service;

import com.polyclinic.mis.models.ExaminationReferral;

import java.util.List;

public interface ExaminationReferralService {
    ExaminationReferral add(ExaminationReferral examinationReferral);
    void delete(Long id);
    ExaminationReferral edit(ExaminationReferral examinationReferral);
    List<ExaminationReferral> getAll();
}
