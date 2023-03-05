package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.DoctorReferral;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.DoctorReferralRepository;
import com.polyclinic.mis.service.DoctorReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorReferralServiceImpl implements DoctorReferralService {
    @Autowired
    private DoctorReferralRepository doctorReferralRepository;
    @Override
    public DoctorReferral add(DoctorReferral doctorReferral) {
        return doctorReferralRepository.saveAndFlush(doctorReferral);
    }
    @Override
    public Optional<DoctorReferral> getById(Long id){
        return doctorReferralRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        doctorReferralRepository.deleteById(id);
    }

    @Override
    public DoctorReferral edit(DoctorReferral doctorReferral) {
        return doctorReferralRepository.saveAndFlush(doctorReferral);
    }

    @Override
    public List<DoctorReferral> getAll() {
        return doctorReferralRepository.findAll();
    }
}
