package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.DoctorAppointmentTime;
import com.polyclinic.mis.models.TherapistAppointmentTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TherapistAppointmentTimeRepository extends JpaRepository<TherapistAppointmentTime,Long> {
    @Query("SELECT a from TherapistAppointmentTime as a where a.doctor.lastName like %:lastName% and a.doctor.firstName like %:firstName% and a.doctor.middleName like %:middleName%")

    public Page<TherapistAppointmentTime> findAll(String lastName, String firstName, String middleName, Pageable pageable);


    @Query("SELECT a from TherapistAppointmentTime as a where a.doctor.id = :doctorId order by a.time asc ")
    public List<TherapistAppointmentTime> findByDoctorId(long doctorId);

    @Query("SELECT a from TherapistAppointmentTime as a where a.doctor.id = :doctorId and a.dayOfTheWeek like %:weekDay% order by a.time asc ")
    public List<TherapistAppointmentTime> findByDoctorIdAndWeekDay(long doctorId, String weekDay);
}
