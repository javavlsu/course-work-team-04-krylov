package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.FunctionalDiagnosticsDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalDiagnosticsDoctorRepository extends JpaRepository<FunctionalDiagnosticsDoctor,Long> {
}
