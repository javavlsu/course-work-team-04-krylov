package com.polyclinic.mis.service;

import com.polyclinic.mis.models.DoctorAppointment;

import java.util.List;

public interface DoctorAppointmentService {
    DoctorAppointment add(DoctorAppointment doctorAppointment);
    void delete(Long id);
    DoctorAppointment edit(DoctorAppointment doctorAppointment);
    List<DoctorAppointment> getAll();
}
