package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Diagnosis;
import com.polyclinic.mis.models.Receptionist;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface DiagnosisService {
    Diagnosis add(Diagnosis diagnosis);

    Optional<Diagnosis> getById(String id);

    void delete(String id);

    List<Diagnosis> findByDescriptionContaining(String description);

    Diagnosis edit(Diagnosis diagnosis);

    List<Diagnosis> getAll();

    Page<Diagnosis> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String id, String description);
}
