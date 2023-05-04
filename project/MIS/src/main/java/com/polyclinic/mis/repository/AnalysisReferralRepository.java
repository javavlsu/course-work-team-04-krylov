package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisReferral;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisReferralRepository extends JpaRepository<AnalysisReferral,Long> {
    @Query("SELECT a from AnalysisReferral as a where a.patient.lastName like %:lastName% and a.patient.firstName like %:firstName% and a.patient.middleName like %:middleName% and FUNCTION('date_format',a.patient.birthDate,'%Y %m %d') like %:date%")

//    @Query("SELECT a from Analysis as a where a.patient.lastName like %?1%")
    public Page<AnalysisReferral> findAll(@Param("lastName") String lastName, String firstName, String middleName, String date, Pageable pageable);
}
