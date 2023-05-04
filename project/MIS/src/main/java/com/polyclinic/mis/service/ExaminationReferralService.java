package com.polyclinic.mis.service;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.ExaminationReferral;
import com.polyclinic.mis.models.Receptionist;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ExaminationReferralService {
    ExaminationReferral add(ExaminationReferral examinationReferral);
    Optional<ExaminationReferral> getById(Long id);
    void delete(Long id);
    ExaminationReferral edit(ExaminationReferral examinationReferral);
    List<ExaminationReferral> getAll();
    Page<ExaminationReferral> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate);

}
