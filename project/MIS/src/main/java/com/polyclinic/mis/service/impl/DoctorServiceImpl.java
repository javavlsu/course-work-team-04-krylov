package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.repository.DoctorRepository;
import com.polyclinic.mis.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Override
    public Doctor add(Doctor doctor) {
        return doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public void delete(Long id) {
    doctorRepository.deleteById(id);
    }

    @Override
    public Doctor edit(Doctor doctor) {
        return doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }
}
