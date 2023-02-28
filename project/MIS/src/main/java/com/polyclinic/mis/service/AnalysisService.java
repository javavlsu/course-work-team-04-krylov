package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Analysis;

import java.util.List;

public interface AnalysisService {
    Analysis add(Analysis analysis);
    void delete(Long id);
    Analysis edit(Analysis analysis);
    List<Analysis> getAll();
}
