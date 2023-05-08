package com.polyclinic.mis.service;

import com.polyclinic.mis.models.DoctorReferralAppointment;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DoctorReferralAppointmentService {
    DoctorReferralAppointment add(DoctorReferralAppointment doctorReferralAppointment);
    Optional<DoctorReferralAppointment> getById(Long id);
    void delete(Long id);
    DoctorReferralAppointment edit(DoctorReferralAppointment doctorReferralAppointment);
    List<DoctorReferralAppointment> getAll();
    Page<DoctorReferralAppointment> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate, String status);
}
