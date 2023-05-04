package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Patient;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient add(Patient patient);
    void delete(Long id);

    Optional<Patient> getById(Long id);
    Patient edit(Patient patient);
    List<Patient> getAll();
    Page<Patient> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate);

}
