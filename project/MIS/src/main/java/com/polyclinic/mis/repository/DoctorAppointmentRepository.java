package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.DoctorAppointment;
import com.polyclinic.mis.models.DoctorReferralAppointment;
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
public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment, Long> {
    @Query("SELECT a from DoctorAppointment as a where a.patient.lastName like %:lastName% and a.patient.firstName like %:firstName% and a.patient.middleName like %:middleName% and FUNCTION('date_format',a.patient.birthDate,'%Y %m %d') like %:date% and a.status like %:status%")
    public Page<DoctorAppointment> findAll(@Param("lastName") String lastName, String firstName, String middleName, String date, String status, Pageable pageable);

    @Query("SELECT a from DoctorAppointment as a where a.status like %:status%")
    public Page<DoctorAppointment> findAll(String status, Pageable pageable);

    @Query("SELECT a from DoctorAppointment as a where a.status like %:status% and a.patient.id = :patientId")
    public Page<DoctorAppointment> findForOnePatient(String status, long patientId, Pageable pageable);

    @Query("SELECT a from DoctorAppointment as a where a.doctor.id = :doctorId and a.date = :date")
    public List<DoctorAppointment> findByDoctorIdAndDate(long doctorId, Date date);

    @Query("SELECT a from DoctorAppointment as a where a.doctor.id = :doctorId and a.date = :date and a.time = :time")
    public List<DoctorAppointment> findByDoctorIdAndDateAndTime(long doctorId, Date date, Time time);

    @Query("SELECT a from DoctorAppointment as a where a.status like %:status% and a.doctor.id = :doctorId")
    public Page<DoctorAppointment> findAllForDoctor(String status, Long doctorId, Pageable pageable);

    @Query("SELECT a from DoctorAppointment as a where a.patient.lastName like %:lastName% and a.patient.firstName like %:firstName% and a.patient.middleName like %:middleName% and FUNCTION('date_format',a.patient.birthDate,'%Y %m %d') like %:date% and a.status like %:status% and a.doctor.id=:doctorId")
    public Page<DoctorAppointment> findAllForDoctor(@Param("lastName") String lastName, String firstName, String middleName, String date, String status,Long doctorId, Pageable pageable);
}
