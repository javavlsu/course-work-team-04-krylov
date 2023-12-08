package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.Inspection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection,Long> {
    @Query("SELECT a from Inspection as a where a.patient.lastName like %:lastName% and a.patient.firstName like %:firstName% and a.patient.middleName like %:middleName% and FUNCTION('date_format',a.patient.birthDate,'%Y %m %d') like %:date%")
    public Page<Inspection> findAll(@Param("lastName") String lastName, String firstName, String middleName, String date, Pageable pageable);

    @Query("SELECT a from Inspection as a where a.patient.id = :patientId")
    public Page<Inspection> findForOnePatient(long patientId, Pageable pageable);

    @Query("SELECT a from Inspection as a where a.patient.id = :patientId order by a.date desc ")
    public List<Inspection> findForOnePatient(long patientId);
}
