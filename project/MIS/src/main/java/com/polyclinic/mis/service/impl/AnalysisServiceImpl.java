package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.*;
import com.polyclinic.mis.repository.AnalysisRepository;
import com.polyclinic.mis.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private AnalysisRepository analysisRepository;
    @Autowired
    private PolyclinicUserServiceImpl polyclinicUserService;


    @Override
    public Analysis add(Analysis analysis) {
        return analysisRepository.saveAndFlush(analysis);
    }
    @Override
    public Optional<Analysis> getById(Long id){
        return analysisRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        analysisRepository.deleteById(id);
    }

    @Override
    public Analysis edit(Analysis analysis) {
        return analysisRepository.saveAndFlush(analysis);
    }

    @Override
    public List<Analysis> getAll() {
        return analysisRepository.findAll();
    }

    @Override
    public Page<Analysis> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (birthDate.equals("")&&fio.equals("")){
            return analysisRepository.findAll(pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return analysisRepository.findAll(splitFio[0],"","","",pageable);
                case 2:
                    return analysisRepository.findAll(splitFio[0],splitFio[1],"","",pageable);
                case 3:
                    return analysisRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return analysisRepository.findAll(splitFio[0],"","",birthDate,pageable);
                case 2:
                    return analysisRepository.findAll(splitFio[0],splitFio[1],"",birthDate,pageable);
                case 3:
                    return analysisRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,pageable);
            }
        }
        else {
            return analysisRepository.findAll("","","",birthDate,pageable);
        }
        return analysisRepository.findAll(pageable);
    }
    @Override
    public Page<Analysis> patientFindPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        var patient = polyclinicUserService.getPatientFromContext();
        return analysisRepository.findForOnePatient(patient.getId(),pageable);

    }

    public Page<Analysis> findPaginatedForCabinet(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        var assistant = polyclinicUserService.getAssistantFromContext();
        if (birthDate.equals("")&&fio.equals("")){
            return analysisRepository.findForCabinetNoSearch(assistant.getCabinet().getId(),pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return analysisRepository.findForCabinet(splitFio[0],"","","",assistant.getCabinet().getId(),pageable);
                case 2:
                    return analysisRepository.findForCabinet(splitFio[0],splitFio[1],"","",assistant.getCabinet().getId(),pageable);
                case 3:
                    return analysisRepository.findForCabinet(splitFio[0],splitFio[1],splitFio[2],"",assistant.getCabinet().getId(),pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return analysisRepository.findForCabinet(splitFio[0],"","",birthDate,assistant.getCabinet().getId(),pageable);
                case 2:
                    return analysisRepository.findForCabinet(splitFio[0],splitFio[1],"",birthDate,assistant.getCabinet().getId(),pageable);
                case 3:
                    return analysisRepository.findForCabinet(splitFio[0],splitFio[1],splitFio[2],birthDate,assistant.getCabinet().getId(),pageable);
            }
        }
        else {
            return analysisRepository.findForCabinet("","","",birthDate,assistant.getCabinet().getId(),pageable);
        }
        return analysisRepository.findForCabinetNoSearch(assistant.getCabinet().getId(),pageable);
    }

    public List<Analysis> getPatientAnalyses(Patient patient){
        List<Analysis> analyses = analysisRepository.findForOnePatient(patient.getId());
        return analyses;
    }
}
