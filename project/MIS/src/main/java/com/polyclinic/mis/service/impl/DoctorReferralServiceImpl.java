package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.DoctorReferral;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.DoctorReferralRepository;
import com.polyclinic.mis.service.DoctorReferralService;
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
public class DoctorReferralServiceImpl implements DoctorReferralService {
    @Autowired
    private DoctorReferralRepository doctorReferralRepository;

    @Autowired
    private PolyclinicUserServiceImpl polyclinicUserService;
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

    @Override
    public Page<DoctorReferral> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (birthDate.equals("")&&fio.equals("")){
            return doctorReferralRepository.findAll(pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return doctorReferralRepository.findAll(splitFio[0],"","","",pageable);
                case 2:
                    return doctorReferralRepository.findAll(splitFio[0],splitFio[1],"","",pageable);
                case 3:
                    return doctorReferralRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return doctorReferralRepository.findAll(splitFio[0],"","",birthDate,pageable);
                case 2:
                    return doctorReferralRepository.findAll(splitFio[0],splitFio[1],"",birthDate,pageable);
                case 3:
                    return doctorReferralRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,pageable);
            }
        }
        else {
            return doctorReferralRepository.findAll("","","",birthDate,pageable);
        }
        return doctorReferralRepository.findAll(pageable);
    }

    @Override
    public Page<DoctorReferral> patientFindPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        var patient = polyclinicUserService.getPatientFromContext();
        return doctorReferralRepository.findForOnePatient(patient.getId(),pageable);

    }
}
