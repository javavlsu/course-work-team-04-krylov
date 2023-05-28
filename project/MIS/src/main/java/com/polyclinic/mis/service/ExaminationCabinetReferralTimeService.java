package com.polyclinic.mis.service;

import com.polyclinic.mis.models.ExaminationCabinetReferralTime;
import com.polyclinic.mis.models.TherapistAppointmentTime;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ExaminationCabinetReferralTimeService {
    ExaminationCabinetReferralTime add(ExaminationCabinetReferralTime examinationCabinetReferralTime);
    Optional<ExaminationCabinetReferralTime> getById(Long id);
    void delete(Long id);
    ExaminationCabinetReferralTime edit(ExaminationCabinetReferralTime examinationCabinetReferralTime);
    List<ExaminationCabinetReferralTime> getAll();

    Page<ExaminationCabinetReferralTime> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String name);

}
