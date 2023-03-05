package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Receptionist;

import java.util.List;
import java.util.Optional;

public interface AnalysisService {
    Analysis add(Analysis analysis);
    Optional<Analysis> getById(Long id);
    void delete(Long id);
    Analysis edit(Analysis analysis);
    List<Analysis> getAll();
}
