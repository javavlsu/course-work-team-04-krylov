package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.DoctorAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment,Long> {
}
