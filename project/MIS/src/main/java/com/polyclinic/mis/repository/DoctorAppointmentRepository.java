package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.DoctorAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment, Long> {
    @Query("SELECT a from DoctorAppointment as a where a.doctorReferral.patient.lastName like %:lastName% and a.doctorReferral.patient.firstName like %:firstName% and a.doctorReferral.patient.middleName like %:middleName% and FUNCTION('date_format',a.doctorReferral.patient.birthDate,'%Y %m %d') like %:date% and a.status like %:status%")
    public Page<DoctorAppointment> findAll(@Param("lastName") String lastName, String firstName, String middleName, String date, String status, Pageable pageable);

    @Query("SELECT a from DoctorAppointment as a where a.status like %:status%")
    public Page<DoctorAppointment> findAll(String status, Pageable pageable);
}
