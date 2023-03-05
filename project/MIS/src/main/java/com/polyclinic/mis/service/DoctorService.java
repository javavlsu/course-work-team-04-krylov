package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.Receptionist;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Doctor add(Doctor doctor);
    Optional<Doctor> getById(Long id);
    void delete(Long id);
    Doctor edit(Doctor doctor);
    List<Doctor> getAll();
}
