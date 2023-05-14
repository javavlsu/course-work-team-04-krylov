package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.models.Receptionist;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface DoctorService {
    Doctor add(Doctor doctor);
    Optional<Doctor> getById(Long id);
    void delete(Long id);
    Doctor edit(Doctor doctor);
    List<Doctor> getAll();

    Page<Doctor> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String speciality);

    String[] getAllSpecialities();

}
