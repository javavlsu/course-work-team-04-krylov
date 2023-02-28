package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Assistant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistantRepository extends JpaRepository<Assistant,Long> {
}
