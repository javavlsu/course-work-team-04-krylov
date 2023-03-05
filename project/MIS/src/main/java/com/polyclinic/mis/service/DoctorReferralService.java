package com.polyclinic.mis.service;

import com.polyclinic.mis.models.DoctorReferral;
import com.polyclinic.mis.models.Receptionist;

import java.util.List;
import java.util.Optional;

public interface DoctorReferralService {
    DoctorReferral add(DoctorReferral doctorReferral);
    Optional<DoctorReferral> getById(Long id);
    void delete(Long id);
    DoctorReferral edit(DoctorReferral doctorReferral);
    List<DoctorReferral> getAll();
}
