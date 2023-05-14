package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.repository.AnalysisReferralRepository;
import com.polyclinic.mis.service.AnalysisReferralService;
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

public class AnalysisReferralServiceImpl implements AnalysisReferralService {
    @Autowired
    private AnalysisReferralRepository analysisReferralRepository;

    @Autowired
    private PolyclinicUserServiceImpl polyclinicUserService;

    @Override
    public AnalysisReferral add(AnalysisReferral analysisReferral) {
        return analysisReferralRepository.saveAndFlush(analysisReferral);
    }
    @Override
    public Optional<AnalysisReferral> getById(Long id){
        return analysisReferralRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        analysisReferralRepository.deleteById(id);
    }

    @Override
    public AnalysisReferral edit(AnalysisReferral analysisReferral) {

        return analysisReferralRepository.saveAndFlush(analysisReferral);
    }

    @Override
    public List<AnalysisReferral> getAll() {
        return analysisReferralRepository.findAll();
    }

    @Override
    public Page<AnalysisReferral> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (birthDate.equals("")&&fio.equals("")){
            return analysisReferralRepository.findAll(pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return analysisReferralRepository.findAll(splitFio[0],"","","",pageable);
                case 2:
                    return analysisReferralRepository.findAll(splitFio[0],splitFio[1],"","",pageable);
                case 3:
                    return analysisReferralRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return analysisReferralRepository.findAll(splitFio[0],"","",birthDate,pageable);
                case 2:
                    return analysisReferralRepository.findAll(splitFio[0],splitFio[1],"",birthDate,pageable);
                case 3:
                    return analysisReferralRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,pageable);
            }
        }
        else {
            return analysisReferralRepository.findAll("","","",birthDate,pageable);
        }
        return analysisReferralRepository.findAll(pageable);
    }
    @Override
    public Page<AnalysisReferral> patientFindPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        var patient = polyclinicUserService.getPatientFromContext();
        return analysisReferralRepository.findForOnePatient(patient.getId(),pageable);

    }
}
