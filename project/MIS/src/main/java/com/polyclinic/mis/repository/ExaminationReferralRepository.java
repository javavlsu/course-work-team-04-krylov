package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.ExaminationReferral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationReferralRepository extends JpaRepository<ExaminationReferral,Long> {
}
