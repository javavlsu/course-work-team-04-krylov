package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.DoctorAppointment;
import com.polyclinic.mis.repository.DoctorAppointmentRepository;
import com.polyclinic.mis.service.DoctorAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {
    @Autowired
    private DoctorAppointmentRepository doctorAppointmentRepository;
    @Override
    public DoctorAppointment add(DoctorAppointment doctorAppointment) {
        return doctorAppointmentRepository.saveAndFlush(doctorAppointment);
    }

    @Override
    public void delete(Long id) {
    doctorAppointmentRepository.deleteById(id);
    }

    @Override
    public DoctorAppointment edit(DoctorAppointment doctorAppointment) {
        return doctorAppointmentRepository.saveAndFlush(doctorAppointment);
    }

    @Override
    public List<DoctorAppointment> getAll() {
        return doctorAppointmentRepository.findAll();
    }
}
