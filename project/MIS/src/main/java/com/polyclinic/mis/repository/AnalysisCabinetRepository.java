package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.AnalysisCabinet;
import com.polyclinic.mis.models.AnalysisReferral;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnalysisCabinetRepository extends JpaRepository<AnalysisCabinet,Long> {

    @Query("SELECT a from AnalysisCabinet as a where a.name like %:name%")

//    @Query("SELECT a from Analysis as a where a.patient.lastName like %?1%")
    public Page<AnalysisCabinet> findAll(String name, Pageable pageable);
}
