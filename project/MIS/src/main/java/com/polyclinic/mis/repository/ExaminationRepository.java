package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination,Long> {
}
