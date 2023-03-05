package com.polyclinic.mis.service;

import com.polyclinic.mis.models.DoctorAppointment;
import com.polyclinic.mis.models.Receptionist;

import java.util.List;
import java.util.Optional;

public interface DoctorAppointmentService {
    DoctorAppointment add(DoctorAppointment doctorAppointment);
    Optional<DoctorAppointment> getById(Long id);
    void delete(Long id);
    DoctorAppointment edit(DoctorAppointment doctorAppointment);
    List<DoctorAppointment> getAll();
}
