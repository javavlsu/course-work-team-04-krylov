package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.DoctorReferral;
import com.polyclinic.mis.models.Receptionist;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DoctorReferralService {
    DoctorReferral add(DoctorReferral doctorReferral);
    Optional<DoctorReferral> getById(Long id);
    void delete(Long id);
    DoctorReferral edit(DoctorReferral doctorReferral);
    List<DoctorReferral> getAll();
    Page<DoctorReferral> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate);
}
