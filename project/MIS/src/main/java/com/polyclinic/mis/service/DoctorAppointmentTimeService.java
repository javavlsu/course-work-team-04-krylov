package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.DoctorAppointmentTime;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface DoctorAppointmentTimeService {
    DoctorAppointmentTime add(DoctorAppointmentTime doctorAppointmentTime);
    Optional<DoctorAppointmentTime> getById(Long id);
    void delete(Long id);
    DoctorAppointmentTime edit(DoctorAppointmentTime doctorAppointmentTime);
    List<DoctorAppointmentTime> getAll();

    Page<DoctorAppointmentTime> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio);

}
