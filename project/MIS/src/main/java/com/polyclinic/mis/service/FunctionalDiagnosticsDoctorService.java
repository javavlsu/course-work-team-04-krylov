package com.polyclinic.mis.service;

import com.polyclinic.mis.models.FunctionalDiagnosticsDoctor;

import java.util.List;

public interface FunctionalDiagnosticsDoctorService {
    FunctionalDiagnosticsDoctor add(FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor);
    void delete(Long id);
    FunctionalDiagnosticsDoctor edit(FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor);
    List<FunctionalDiagnosticsDoctor> getAll();
}
