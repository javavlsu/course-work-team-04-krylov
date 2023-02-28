package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.AnalysisReferral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisReferralRepository extends JpaRepository<AnalysisReferral,Long> {
}
