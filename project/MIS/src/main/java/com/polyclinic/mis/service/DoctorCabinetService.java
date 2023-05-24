package com.polyclinic.mis.service;

import com.polyclinic.mis.models.AnalysisCabinet;
import com.polyclinic.mis.models.DoctorCabinet;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface DoctorCabinetService {
    DoctorCabinet add(DoctorCabinet doctorCabinet);
    Optional<DoctorCabinet> getById(Long id);
    void delete(Long id);
    DoctorCabinet edit(DoctorCabinet doctorCabinet);
    List<DoctorCabinet> getAll();

//    Page<Analysis> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
    Page<DoctorCabinet> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String name);

//    Page<Analysis> findPaginatedWithSearch(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, Date birthDate);

}
