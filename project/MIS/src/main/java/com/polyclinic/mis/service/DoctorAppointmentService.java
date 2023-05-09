package com.polyclinic.mis.service;

import com.polyclinic.mis.models.DoctorAppointment;
import com.polyclinic.mis.models.DoctorReferralAppointment;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DoctorAppointmentService {
    DoctorAppointment add(DoctorAppointment doctorAppointment);
    Optional<DoctorAppointment> getById(Long id);
    void delete(Long id);
    DoctorAppointment edit(DoctorAppointment doctorAppointment);
    List<DoctorAppointment> getAll();
    Page<DoctorAppointment> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate, String status);

    Page<DoctorAppointment> patientFindPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String status);
}
