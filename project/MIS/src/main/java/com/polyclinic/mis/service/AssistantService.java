package com.polyclinic.mis.service;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.models.Receptionist;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface AssistantService {
    Assistant add(Assistant assistant);
    Optional<Assistant> getById(Long id);
    void delete(Long id);
    Assistant edit(Assistant assistant);
    List<Assistant> getAll();

    Page<Assistant> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate);

}
