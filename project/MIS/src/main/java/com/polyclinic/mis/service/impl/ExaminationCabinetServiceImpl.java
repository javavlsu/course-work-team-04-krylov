package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.AnalysisCabinet;
import com.polyclinic.mis.models.ExaminationCabinet;
import com.polyclinic.mis.repository.AnalysisCabinetRepository;
import com.polyclinic.mis.repository.ExaminationCabinetRepository;
import com.polyclinic.mis.service.AnalysisCabinetService;
import com.polyclinic.mis.service.ExaminationCabinetService;
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
public class ExaminationCabinetServiceImpl implements ExaminationCabinetService {
    @Autowired
    private ExaminationCabinetRepository examinationCabinetRepository;
    @Override
    public ExaminationCabinet add(ExaminationCabinet examinationCabinet) {
        return examinationCabinetRepository.saveAndFlush(examinationCabinet);
    }
    @Override
    public Optional<ExaminationCabinet> getById(Long id){
        return examinationCabinetRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        examinationCabinetRepository.deleteById(id);
    }

    @Override
    public ExaminationCabinet edit(ExaminationCabinet examinationCabinet) {
        return examinationCabinetRepository.saveAndFlush(examinationCabinet);
    }

    @Override
    public List<ExaminationCabinet> getAll() {
        return examinationCabinetRepository.findAll();
    }

    @Override
    public Page<ExaminationCabinet> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String name) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (name.equals("")){
            return examinationCabinetRepository.findAll(pageable);
        }
        else{
            return examinationCabinetRepository.findAll(name,pageable);
            }
    }

}
