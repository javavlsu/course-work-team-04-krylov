package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.ExaminationCabinetReferralTime;
import com.polyclinic.mis.models.TherapistAppointmentTime;
import com.polyclinic.mis.repository.ExaminationCabinetReferralTimeRepository;
import com.polyclinic.mis.repository.TherapistAppointmentTimeRepository;
import com.polyclinic.mis.service.ExaminationCabinetReferralTimeService;
import com.polyclinic.mis.service.TherapistAppointmentTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExaminationCabinetReferralTimeServiceImpl implements ExaminationCabinetReferralTimeService {
    @Autowired
    private ExaminationCabinetReferralTimeRepository examinationCabinetReferralTimeRepository;

    @Override
    public ExaminationCabinetReferralTime add(ExaminationCabinetReferralTime examinationCabinetReferralTime) {
        return examinationCabinetReferralTimeRepository.saveAndFlush(examinationCabinetReferralTime);
    }
    @Override
    public Optional<ExaminationCabinetReferralTime> getById(Long id){
        return examinationCabinetReferralTimeRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        examinationCabinetReferralTimeRepository.deleteById(id);
    }

    @Override
    public ExaminationCabinetReferralTime edit(ExaminationCabinetReferralTime examinationCabinetReferralTime) {
        return examinationCabinetReferralTimeRepository.saveAndFlush(examinationCabinetReferralTime);
    }

    @Override
    public List<ExaminationCabinetReferralTime> getAll() {
        return examinationCabinetReferralTimeRepository.findAll();
    }

    public List<ExaminationCabinetReferralTime> getByCabinetId(long id) {
        return examinationCabinetReferralTimeRepository.findByCabinetId(id);
    }

    @Override
    public Page<ExaminationCabinetReferralTime> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String name) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (name.equals("")){
            return examinationCabinetReferralTimeRepository.findAll(pageable);
        }
        else{
            examinationCabinetReferralTimeRepository.findAll(name,pageable);
        }
        return examinationCabinetReferralTimeRepository.findAll(pageable);
    }

}
