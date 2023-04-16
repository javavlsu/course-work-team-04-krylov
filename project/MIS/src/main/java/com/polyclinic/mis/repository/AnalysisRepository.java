package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Analysis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis,Long> {
    @Query("SELECT a from Analysis as a where a.patient.lastName like %?1% and a.patient.firstName like %?2% and a.patient.middleName like %?3% and FUNCTION('date_format',a.patient.birthDate,'%Y-%m-%d') like %?4%")

//    @Query("SELECT a from Analysis as a where a.patient.lastName like %?1%")
    public Page<Analysis> findAll(String lastName, String firstName, String middleName, String date, Pageable pageable);
}
