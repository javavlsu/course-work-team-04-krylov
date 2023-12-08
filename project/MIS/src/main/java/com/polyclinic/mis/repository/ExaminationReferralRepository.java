package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface ExaminationReferralRepository extends JpaRepository<ExaminationReferral,Long> {
    @Query("SELECT a from ExaminationReferral as a where a.patient.lastName like %:lastName% and a.patient.firstName like %:firstName% and a.patient.middleName like %:middleName% and FUNCTION('date_format',a.patient.birthDate,'%Y %m %d') like %:date%")

//    @Query("SELECT a from Analysis as a where a.patient.lastName like %?1%")
    public Page<ExaminationReferral> findAll(@Param("lastName") String lastName, String firstName, String middleName, String date, Pageable pageable);

    @Query("SELECT a from ExaminationReferral as a where a.patient.id = :patientId")
    public Page<ExaminationReferral> findForOnePatient(long patientId, Pageable pageable);


    @Query("SELECT a from ExaminationReferral as a where a.cabinet.id = :cabinetId and a.date = :date")
    public List<ExaminationReferral> findByCabinetIdAndDate(long cabinetId, Date date);

    @Query("SELECT a from ExaminationReferral as a where a.cabinet.id = :cabinetId and a.date = :date and a.time = :time")
    public List<ExaminationReferral> findByCabinetIdAndDateAndTime(long cabinetId, Date date, Time time);

    @Query("SELECT a from ExaminationReferral as a where a.patient.lastName like %:lastName% and a.patient.firstName like %:firstName% and a.patient.middleName like %:middleName% and FUNCTION('date_format',a.patient.birthDate,'%Y %m %d') like %:date% and a.cabinet.id=:cabinetId")
    public Page<ExaminationReferral> findForCabinet(@Param("lastName") String lastName, String firstName, String middleName, String date, long cabinetId,Pageable pageable);


    @Query("SELECT a from ExaminationReferral as a where a.cabinet.id=:cabinetId")
    public Page<ExaminationReferral> findForCabinetNoSearch(long cabinetId, Pageable pageable);

    @Query("SELECT a from ExaminationReferral as a where a.patient.id = :patientId order by a.dateOfIssue desc ")
    public List<ExaminationReferral> findForOnePatient(long patientId);
}
