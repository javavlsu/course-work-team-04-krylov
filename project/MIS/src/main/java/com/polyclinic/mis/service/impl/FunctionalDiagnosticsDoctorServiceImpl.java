package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.FunctionalDiagnosticsDoctor;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.FunctionalDiagnosticsDoctorRepository;
import com.polyclinic.mis.service.FunctionalDiagnosticsDoctorService;
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
public class FunctionalDiagnosticsDoctorServiceImpl implements FunctionalDiagnosticsDoctorService {
    @Autowired
    public FunctionalDiagnosticsDoctorRepository functionalDiagnosticsDoctorRepository;

    @Autowired
    private PolyclinicUserServiceImpl polyclinicUserService;
    @Override
    public FunctionalDiagnosticsDoctor add(FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor) {
        return functionalDiagnosticsDoctorRepository.saveAndFlush(functionalDiagnosticsDoctor);
    }
    @Override
    public Optional<FunctionalDiagnosticsDoctor> getById(Long id){
        return functionalDiagnosticsDoctorRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        functionalDiagnosticsDoctorRepository.deleteById(id);
    }

    @Override
    public FunctionalDiagnosticsDoctor edit(FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor) {
        return functionalDiagnosticsDoctorRepository.saveAndFlush(functionalDiagnosticsDoctor);
    }

    @Override
    public List<FunctionalDiagnosticsDoctor> getAll() {
        return functionalDiagnosticsDoctorRepository.findAll();
    }

    @Override
    public Page<FunctionalDiagnosticsDoctor> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (birthDate.equals("")&&fio.equals("")){
            return functionalDiagnosticsDoctorRepository.findAll(pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return functionalDiagnosticsDoctorRepository.findAll(splitFio[0],"","","",pageable);
                case 2:
                    return functionalDiagnosticsDoctorRepository.findAll(splitFio[0],splitFio[1],"","",pageable);
                case 3:
                    return functionalDiagnosticsDoctorRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return functionalDiagnosticsDoctorRepository.findAll(splitFio[0],"","",birthDate,pageable);
                case 2:
                    return functionalDiagnosticsDoctorRepository.findAll(splitFio[0],splitFio[1],"",birthDate,pageable);
                case 3:
                    return functionalDiagnosticsDoctorRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,pageable);
            }
        }
        else {
            return functionalDiagnosticsDoctorRepository.findAll("","","",birthDate,pageable);
        }
        return functionalDiagnosticsDoctorRepository.findAll(pageable);
    }


    public FunctionalDiagnosticsDoctor currentFunctionalDiagnosticsDoctor(){
        return polyclinicUserService.getFunctionalDiagnosticsDoctorFromContext();
    }
}
