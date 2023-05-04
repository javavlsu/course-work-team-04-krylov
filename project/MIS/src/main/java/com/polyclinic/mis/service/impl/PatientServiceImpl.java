package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.PatientRepository;
import com.polyclinic.mis.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public Patient add(Patient patient) {
        if (patient.getPolisEndDate()!=null&&patient.getPolisEndDateString()!="")
        {
            patient.setPolisEndDate(Date.valueOf(patient.getPolisEndDateString()));
        }
        return patientRepository.saveAndFlush(patient);
    }
    @Override
    public Optional<Patient> getById(Long id){
        return patientRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient edit(Patient patient) {
        return patientRepository.saveAndFlush(patient);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Page<Patient> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (birthDate.equals("")&&fio.equals("")){
            return patientRepository.findAll(pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return patientRepository.findAll(splitFio[0],"","","",pageable);
                case 2:
                    return patientRepository.findAll(splitFio[0],splitFio[1],"","",pageable);
                case 3:
                    return patientRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return patientRepository.findAll(splitFio[0],"","",birthDate,pageable);
                case 2:
                    return patientRepository.findAll(splitFio[0],splitFio[1],"",birthDate,pageable);
                case 3:
                    return patientRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,pageable);
            }
        }
        else {
            return patientRepository.findAll("","","",birthDate,pageable);
        }
        return patientRepository.findAll(pageable);
    }
}
