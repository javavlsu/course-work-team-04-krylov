package com.polyclinic.mis.service;

import com.polyclinic.mis.models.DoctorReferral;

import java.util.List;

public interface DoctorReferralService {
    DoctorReferral add(DoctorReferral doctorReferral);
    void delete(Long id);
    DoctorReferral edit(DoctorReferral doctorReferral);
    List<DoctorReferral> getAll();
}
