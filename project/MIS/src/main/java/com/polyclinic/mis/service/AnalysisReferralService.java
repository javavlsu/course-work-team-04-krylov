package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Receptionist;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AnalysisReferralService {
    AnalysisReferral add(AnalysisReferral analysisReferral);

    Optional<AnalysisReferral> getById(Long id);

    void delete(Long id);
    AnalysisReferral edit(AnalysisReferral analysisReferral);
    List<AnalysisReferral> getAll();
    Page<AnalysisReferral> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate);
}
