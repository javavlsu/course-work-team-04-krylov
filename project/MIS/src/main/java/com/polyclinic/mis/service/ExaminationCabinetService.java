package com.polyclinic.mis.service;

import com.polyclinic.mis.models.AnalysisCabinet;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.ExaminationCabinet;
import com.polyclinic.mis.repository.ExaminationCabinetRepository;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ExaminationCabinetService {
    ExaminationCabinet add(ExaminationCabinet examinationCabinet);
    Optional<ExaminationCabinet> getById(Long id);
    void delete(Long id);
    ExaminationCabinet edit(ExaminationCabinet examinationCabinet);
    List<ExaminationCabinet> getAll();

//    Page<Analysis> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
    Page<ExaminationCabinet> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String name);

//    Page<Analysis> findPaginatedWithSearch(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, Date birthDate);

}
