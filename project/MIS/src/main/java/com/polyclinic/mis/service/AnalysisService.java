package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Receptionist;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface AnalysisService {
    Analysis add(Analysis analysis);
    Optional<Analysis> getById(Long id);
    void delete(Long id);
    Analysis edit(Analysis analysis);
    List<Analysis> getAll();

//    Page<Analysis> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
    Page<Analysis> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate);

//    Page<Analysis> findPaginatedWithSearch(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, Date birthDate);
    Page<Analysis> patientFindPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);

}
