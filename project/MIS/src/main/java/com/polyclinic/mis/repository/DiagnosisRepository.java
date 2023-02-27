package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis,String> {
    List<Diagnosis> findByDescriptionContaining(String description);
}
