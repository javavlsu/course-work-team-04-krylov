package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.ExaminationReferral;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisReferralRepository extends JpaRepository<AnalysisReferral,Long> {
    @Query("SELECT a from AnalysisReferral as a where a.patient.lastName like %:lastName% and a.patient.firstName like %:firstName% and a.patient.middleName like %:middleName% and FUNCTION('date_format',a.patient.birthDate,'%Y %m %d') like %:date%")
    public Page<AnalysisReferral> findAll(@Param("lastName") String lastName, String firstName, String middleName, String date, Pageable pageable);

    @Query("SELECT a from AnalysisReferral as a where a.patient.id = :patientId")
    public Page<AnalysisReferral> findForOnePatient(long patientId, Pageable pageable);

    @Query("SELECT a from AnalysisReferral as a where a.patient.lastName like %:lastName% and a.patient.firstName like %:firstName% and a.patient.middleName like %:middleName% and FUNCTION('date_format',a.patient.birthDate,'%Y %m %d') like %:date% and a.cabinet.id=:cabinetId")
    public Page<AnalysisReferral> findForCabinet(@Param("lastName") String lastName, String firstName, String middleName, String date, long cabinetId,Pageable pageable);


    @Query("SELECT a from AnalysisReferral as a where a.cabinet.id=:cabinetId")
    public Page<AnalysisReferral> findForCabinetNoSearch(long cabinetId, Pageable pageable);

//    @Query("SELECT a from AnalysisReferral as a where a.cabinet.id = :cabinetId")
//    public Page<AnalysisReferral> findForCabinet(long cabinetId, Pageable pageable);

    @Query("SELECT a from AnalysisReferral as a where a.patient.id = :patientId order by a.dateOfIssue desc ")
    public List<AnalysisReferral> findForOnePatient(long patientId);
}
