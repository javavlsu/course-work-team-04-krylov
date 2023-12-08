package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Examination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis,Long> {
    @Query("SELECT a from Analysis as a where a.patient.lastName like %:lastName% and a.patient.firstName like %:firstName% and a.patient.middleName like %:middleName% and FUNCTION('date_format',a.patient.birthDate,'%Y %m %d') like %:date%")

//    @Query("SELECT a from Analysis as a where a.patient.lastName like %?1%")
    public Page<Analysis> findAll(@Param("lastName") String lastName, String firstName, String middleName, String date, Pageable pageable);
    @Query("SELECT a from Analysis as a where a.patient.id = :patientId")
    public Page<Analysis> findForOnePatient(long patientId, Pageable pageable);


    @Query("SELECT a from Analysis as a where a.patient.lastName like %:lastName% and a.patient.firstName like %:firstName% and a.patient.middleName like %:middleName% and FUNCTION('date_format',a.patient.birthDate,'%Y %m %d') like %:date% and a.analysisReferral.cabinet.id=:cabinetId")
    public Page<Analysis> findForCabinet(@Param("lastName") String lastName, String firstName, String middleName, String date, long cabinetId, Pageable pageable);


    @Query("SELECT a from Analysis as a where a.analysisReferral.cabinet.id=:cabinetId")
    public Page<Analysis> findForCabinetNoSearch(long cabinetId, Pageable pageable);

    @Query("SELECT a from Analysis as a where a.patient.id = :patientId order by a.date desc ")
    public List<Analysis> findForOnePatient(long patientId);
}
