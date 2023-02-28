package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection,Long> {
}
