package com.polyclinic.mis.service;

import com.polyclinic.mis.models.FunctionalDiagnosticsDoctor;
import com.polyclinic.mis.models.Receptionist;

import java.util.List;
import java.util.Optional;

public interface FunctionalDiagnosticsDoctorService {
    FunctionalDiagnosticsDoctor add(FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor);
    Optional<FunctionalDiagnosticsDoctor> getById(Long id);
    void delete(Long id);
    FunctionalDiagnosticsDoctor edit(FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor);
    List<FunctionalDiagnosticsDoctor> getAll();
}
