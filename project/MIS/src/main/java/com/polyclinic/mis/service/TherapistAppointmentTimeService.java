package com.polyclinic.mis.service;

import com.polyclinic.mis.models.DoctorAppointmentTime;
import com.polyclinic.mis.models.TherapistAppointmentTime;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface TherapistAppointmentTimeService {
    TherapistAppointmentTime add(TherapistAppointmentTime therapistAppointmentTime);
    Optional<TherapistAppointmentTime> getById(Long id);
    void delete(Long id);
    TherapistAppointmentTime edit(TherapistAppointmentTime therapistAppointmentTime);
    List<TherapistAppointmentTime> getAll();

    Page<TherapistAppointmentTime> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio);

}
