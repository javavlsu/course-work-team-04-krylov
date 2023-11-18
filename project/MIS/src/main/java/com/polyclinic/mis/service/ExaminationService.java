package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.Receptionist;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ExaminationService {
    Examination add(Examination examination);
    Optional<Examination> getById(Long id);
    void delete(Long id);
    Examination edit(Examination examination);
    List<Examination> getAll();
    Page<Examination> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate);

    Page<Examination> patientFindPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);

}
