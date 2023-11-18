package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisCabinet;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface AnalysisCabinetService {
    AnalysisCabinet add(AnalysisCabinet analysisCabinet);
    Optional<AnalysisCabinet> getById(Long id);
    void delete(Long id);
    AnalysisCabinet edit(AnalysisCabinet analysisCabinet);
    List<AnalysisCabinet> getAll();

//    Page<Analysis> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
    Page<AnalysisCabinet> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String name);

//    Page<Analysis> findPaginatedWithSearch(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, Date birthDate);

}
