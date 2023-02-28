package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.DoctorReferral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorReferralRepository extends JpaRepository<DoctorReferral,Long> {
}
