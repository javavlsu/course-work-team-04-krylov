package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.ExaminationCabinetReferralTime;
import com.polyclinic.mis.models.TherapistAppointmentTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExaminationCabinetReferralTimeRepository extends JpaRepository<ExaminationCabinetReferralTime,Long> {
    @Query("SELECT a from ExaminationCabinetReferralTime as a where a.examinationCabinet.name like %:name%")

    public Page<ExaminationCabinetReferralTime> findAll(String name, Pageable pageable);


    @Query("SELECT a from ExaminationCabinetReferralTime as a where a.examinationCabinet.id = :cabinetId order by a.time asc ")
    public List<ExaminationCabinetReferralTime> findByCabinetId(long cabinetId);

//    @Query("SELECT a from ExaminationCabinetReferralTime as a where a.doctor.id = :doctorId and a.dayOfTheWeek like %:weekDay% order by a.time asc ")
//    public List<ExaminationCabinetReferralTime> findByDoctorIdAndWeekDay(long doctorId, String weekDay);
}
