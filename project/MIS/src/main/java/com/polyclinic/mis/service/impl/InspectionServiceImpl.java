package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Inspection;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.InspectionRepository;
import com.polyclinic.mis.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InspectionServiceImpl implements InspectionService {
    @Autowired
    private InspectionRepository inspectionRepository;
    @Override
    public Inspection add(Inspection inspection) {
        return inspectionRepository.saveAndFlush(inspection);
    }
    @Override
    public Optional<Inspection> getById(Long id){
        return inspectionRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        inspectionRepository.deleteById(id);
    }

    @Override
    public Inspection edit(Inspection inspection) {
        return inspectionRepository.saveAndFlush(inspection);
    }

    @Override
    public List<Inspection> getAll() {
        return inspectionRepository.findAll();
    }

    @Override
    public Page<Inspection> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (birthDate.equals("")&&fio.equals("")){
            return inspectionRepository.findAll(pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return inspectionRepository.findAll(splitFio[0],"","","",pageable);
                case 2:
                    return inspectionRepository.findAll(splitFio[0],splitFio[1],"","",pageable);
                case 3:
                    return inspectionRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return inspectionRepository.findAll(splitFio[0],"","",birthDate,pageable);
                case 2:
                    return inspectionRepository.findAll(splitFio[0],splitFio[1],"",birthDate,pageable);
                case 3:
                    return inspectionRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,pageable);
            }
        }
        else {
            return inspectionRepository.findAll("","","",birthDate,pageable);
        }
        return inspectionRepository.findAll(pageable);
    }
}
