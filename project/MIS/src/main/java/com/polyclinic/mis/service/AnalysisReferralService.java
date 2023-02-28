package com.polyclinic.mis.service;

import com.polyclinic.mis.models.AnalysisReferral;

import java.util.List;

public interface AnalysisReferralService {
    AnalysisReferral add(AnalysisReferral analysisReferral);
    void delete(Long id);
    AnalysisReferral edit(AnalysisReferral analysisReferral);
    List<AnalysisReferral> getAll();
}
