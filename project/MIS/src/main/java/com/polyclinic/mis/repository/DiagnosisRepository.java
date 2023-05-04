package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Diagnosis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis,String> {
    List<Diagnosis> findByDescriptionContaining(String description);

    @Query("SELECT a from Diagnosis as a where a.description like %:description% and a.id like %:id%")
    public Page<Diagnosis> findAll(String description, String id, Pageable pageable);
}
