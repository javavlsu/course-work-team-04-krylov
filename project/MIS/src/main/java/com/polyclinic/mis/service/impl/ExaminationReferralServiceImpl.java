package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.ExaminationReferral;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.ExaminationReferralRepository;
import com.polyclinic.mis.repository.ExaminationRepository;
import com.polyclinic.mis.service.ExaminationReferralService;
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
public class ExaminationReferralServiceImpl implements ExaminationReferralService {
    @Autowired
    private ExaminationReferralRepository examinationReferralRepository;

    @Autowired
    private PolyclinicUserServiceImpl polyclinicUserService;
    @Override
    public ExaminationReferral add(ExaminationReferral examinationReferral) {
        return examinationReferralRepository.saveAndFlush(examinationReferral);
    }
    @Override
    public Optional<ExaminationReferral> getById(Long id){
        return examinationReferralRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        examinationReferralRepository.deleteById(id);
    }

    @Override
    public ExaminationReferral edit(ExaminationReferral examinationReferral) {
        return examinationReferralRepository.saveAndFlush(examinationReferral);
    }

    @Override
    public List<ExaminationReferral> getAll() {
        return examinationReferralRepository.findAll();
    }

    @Override
    public Page<ExaminationReferral> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (birthDate.equals("")&&fio.equals("")){
            return examinationReferralRepository.findAll(pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return examinationReferralRepository.findAll(splitFio[0],"","","",pageable);
                case 2:
                    return examinationReferralRepository.findAll(splitFio[0],splitFio[1],"","",pageable);
                case 3:
                    return examinationReferralRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return examinationReferralRepository.findAll(splitFio[0],"","",birthDate,pageable);
                case 2:
                    return examinationReferralRepository.findAll(splitFio[0],splitFio[1],"",birthDate,pageable);
                case 3:
                    return examinationReferralRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,pageable);
            }
        }
        else {
            return examinationReferralRepository.findAll("","","",birthDate,pageable);
        }
        return examinationReferralRepository.findAll(pageable);
    }
    @Override
    public Page<ExaminationReferral> patientFindPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        var patient = polyclinicUserService.getPatientFromContext();
        return examinationReferralRepository.findForOnePatient(patient.getId(),pageable);

    }
}
