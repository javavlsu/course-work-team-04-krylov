package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("select a.speciality from Doctor as a group by a.speciality")
    String[] findAllSpecialities();

    @Query("SELECT a from Doctor as a where a.lastName like %:lastName% and a.firstName like %:firstName% and a.middleName like %:middleName% and a.speciality like %:speciality%")
    Page<Doctor> findAll(@Param("lastName") String lastName, String firstName, String middleName, String speciality, Pageable pageable);

    @Query("select a from Doctor as a where a.speciality = 'Терапевт'")
    List<Doctor> findAllTherapists();
}
