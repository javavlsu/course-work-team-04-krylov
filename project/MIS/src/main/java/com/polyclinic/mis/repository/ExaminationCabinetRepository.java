package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.AnalysisCabinet;
import com.polyclinic.mis.models.ExaminationCabinet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExaminationCabinetRepository extends JpaRepository<ExaminationCabinet,Long> {

    @Query("SELECT a from ExaminationCabinet as a where a.name like %:name%")

//    @Query("SELECT a from Analysis as a where a.patient.lastName like %?1%")
    public Page<ExaminationCabinet> findAll(String name, Pageable pageable);
}
