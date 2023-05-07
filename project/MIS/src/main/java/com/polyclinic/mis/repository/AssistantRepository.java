package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Assistant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistantRepository extends JpaRepository<Assistant,Long> {
    @Query("SELECT a from Assistant as a where a.lastName like %:lastName% and a.firstName like %:firstName% and a.middleName like %:middleName% and FUNCTION('date_format',a.birthDate,'%Y %m %d') like %:date%")
    public Page<Assistant> findAll(@Param("lastName") String lastName, String firstName, String middleName, String date, Pageable pageable);
}
