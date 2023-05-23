package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.FunctionalDiagnosticsDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FunctionalDiagnosticsDoctorRepository extends JpaRepository<FunctionalDiagnosticsDoctor,Long> {

    @Query("select a from FunctionalDiagnosticsDoctor as a where a.user.id = :id")
    public Optional<FunctionalDiagnosticsDoctor> findByUserId(Long id);
}
