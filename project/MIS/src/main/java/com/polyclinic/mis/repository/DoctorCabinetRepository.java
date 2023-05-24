package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.AnalysisCabinet;
import com.polyclinic.mis.models.DoctorCabinet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorCabinetRepository extends JpaRepository<DoctorCabinet,Long> {
    @Query("SELECT a from DoctorCabinet as a where a.name like %:name%")

//    @Query("SELECT a from Analysis as a where a.patient.lastName like %?1%")
    public Page<DoctorCabinet> findAll(String name, Pageable pageable);
}
