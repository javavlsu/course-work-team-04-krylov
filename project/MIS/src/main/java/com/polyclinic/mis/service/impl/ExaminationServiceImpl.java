package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.Inspection;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.ExaminationRepository;
import com.polyclinic.mis.service.ExaminationService;
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
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    private ExaminationRepository examinationRepository;
    @Autowired
    private PolyclinicUserServiceImpl polyclinicUserService;
    @Override
    public Examination add(Examination examination) {
        return examinationRepository.saveAndFlush(examination);
    }
    @Override
    public Optional<Examination> getById(Long id){
        return examinationRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        examinationRepository.deleteById(id);
    }

    @Override
    public Examination edit(Examination examination) {
        return examinationRepository.saveAndFlush(examination);
    }

    @Override
    public List<Examination> getAll() {
        return examinationRepository.findAll();
    }

    @Override
    public Page<Examination> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (birthDate.equals("")&&fio.equals("")){
            return examinationRepository.findAll(pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return examinationRepository.findAll(splitFio[0],"","","",pageable);
                case 2:
                    return examinationRepository.findAll(splitFio[0],splitFio[1],"","",pageable);
                case 3:
                    return examinationRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return examinationRepository.findAll(splitFio[0],"","",birthDate,pageable);
                case 2:
                    return examinationRepository.findAll(splitFio[0],splitFio[1],"",birthDate,pageable);
                case 3:
                    return examinationRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,pageable);
            }
        }
        else {
            return examinationRepository.findAll("","","",birthDate,pageable);
        }
        return examinationRepository.findAll(pageable);
    }
    @Override
    public Page<Examination> patientFindPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        var patient = polyclinicUserService.getPatientFromContext();
        return examinationRepository.findForOnePatient(patient.getId(),pageable);

    }

    public Page<Examination> findPaginatedForCabinet(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        var functionalDiagnosticsDoctor = polyclinicUserService.getFunctionalDiagnosticsDoctorFromContext();
        if (birthDate.equals("")&&fio.equals("")){
            return examinationRepository.findForCabinetNoSearch(functionalDiagnosticsDoctor.getCabinet().getId(),pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return examinationRepository.findForCabinet(splitFio[0],"","","",functionalDiagnosticsDoctor.getCabinet().getId(),pageable);
                case 2:
                    return examinationRepository.findForCabinet(splitFio[0],splitFio[1],"","",functionalDiagnosticsDoctor.getCabinet().getId(),pageable);
                case 3:
                    return examinationRepository.findForCabinet(splitFio[0],splitFio[1],splitFio[2],"",functionalDiagnosticsDoctor.getCabinet().getId(),pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return examinationRepository.findForCabinet(splitFio[0],"","",birthDate,functionalDiagnosticsDoctor.getCabinet().getId(),pageable);
                case 2:
                    return examinationRepository.findForCabinet(splitFio[0],splitFio[1],"",birthDate,functionalDiagnosticsDoctor.getCabinet().getId(),pageable);
                case 3:
                    return examinationRepository.findForCabinet(splitFio[0],splitFio[1],splitFio[2],birthDate,functionalDiagnosticsDoctor.getCabinet().getId(),pageable);
            }
        }
        else {
            return examinationRepository.findForCabinet("","","",birthDate,functionalDiagnosticsDoctor.getCabinet().getId(),pageable);
        }
        return examinationRepository.findForCabinetNoSearch(functionalDiagnosticsDoctor.getCabinet().getId(),pageable);
    }
    public List<Examination> getPatientExaminations(Patient patient){
        List<Examination> examinations = examinationRepository.findForOnePatient(patient.getId());
        return examinations;
    }

}
