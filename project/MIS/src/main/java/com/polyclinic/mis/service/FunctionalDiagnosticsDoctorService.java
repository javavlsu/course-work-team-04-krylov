package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.FunctionalDiagnosticsDoctor;
import com.polyclinic.mis.models.Receptionist;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface FunctionalDiagnosticsDoctorService {
    FunctionalDiagnosticsDoctor add(FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor);
    Optional<FunctionalDiagnosticsDoctor> getById(Long id);
    void delete(Long id);
    FunctionalDiagnosticsDoctor edit(FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor);
    List<FunctionalDiagnosticsDoctor> getAll();

    Page<FunctionalDiagnosticsDoctor> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate);
}
