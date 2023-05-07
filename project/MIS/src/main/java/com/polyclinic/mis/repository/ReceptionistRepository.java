package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.Receptionist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist,Long> {
    @Query("SELECT a from Receptionist as a where a.lastName like %:lastName% and a.firstName like %:firstName% and a.middleName like %:middleName% and FUNCTION('date_format',a.birthDate,'%Y %m %d') like %:date%")
    public Page<Receptionist> findAll(@Param("lastName") String lastName, String firstName, String middleName, String date, Pageable pageable);
}
