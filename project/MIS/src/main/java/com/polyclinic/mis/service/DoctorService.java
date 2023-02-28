package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor add(Doctor doctor);
    void delete(Long id);
    Doctor edit(Doctor doctor);
    List<Doctor> getAll();
}
