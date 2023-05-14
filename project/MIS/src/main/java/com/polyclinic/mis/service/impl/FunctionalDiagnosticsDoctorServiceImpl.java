package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.FunctionalDiagnosticsDoctor;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.FunctionalDiagnosticsDoctorRepository;
import com.polyclinic.mis.service.FunctionalDiagnosticsDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FunctionalDiagnosticsDoctorServiceImpl implements FunctionalDiagnosticsDoctorService {
    @Autowired
    public FunctionalDiagnosticsDoctorRepository functionalDiagnosticsDoctorRepository;
    @Override
    public FunctionalDiagnosticsDoctor add(FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor) {
        return functionalDiagnosticsDoctorRepository.saveAndFlush(functionalDiagnosticsDoctor);
    }
    @Override
    public Optional<FunctionalDiagnosticsDoctor> getById(Long id){
        return functionalDiagnosticsDoctorRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        functionalDiagnosticsDoctorRepository.deleteById(id);
    }

    @Override
    public FunctionalDiagnosticsDoctor edit(FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor) {
        return functionalDiagnosticsDoctorRepository.saveAndFlush(functionalDiagnosticsDoctor);
    }

    @Override
    public List<FunctionalDiagnosticsDoctor> getAll() {
        return functionalDiagnosticsDoctorRepository.findAll();
    }
}
