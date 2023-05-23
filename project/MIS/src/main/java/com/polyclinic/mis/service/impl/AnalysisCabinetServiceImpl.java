package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.AnalysisCabinet;
import com.polyclinic.mis.repository.AnalysisCabinetRepository;
import com.polyclinic.mis.repository.AnalysisRepository;
import com.polyclinic.mis.service.AnalysisCabinetService;
import com.polyclinic.mis.service.AnalysisService;
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
public class AnalysisCabinetServiceImpl implements AnalysisCabinetService {
    @Autowired
    private AnalysisCabinetRepository analysisCabinetRepository;
    @Override
    public AnalysisCabinet add(AnalysisCabinet analysisCabinet) {
        return analysisCabinetRepository.saveAndFlush(analysisCabinet);
    }
    @Override
    public Optional<AnalysisCabinet> getById(Long id){
        return analysisCabinetRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        analysisCabinetRepository.deleteById(id);
    }

    @Override
    public AnalysisCabinet edit(AnalysisCabinet analysisCabinet) {
        return analysisCabinetRepository.saveAndFlush(analysisCabinet);
    }

    @Override
    public List<AnalysisCabinet> getAll() {
        return analysisCabinetRepository.findAll();
    }

    @Override
    public Page<AnalysisCabinet> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String name) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (name.equals("")){
            return analysisCabinetRepository.findAll(pageable);
        }
        else{
            return analysisCabinetRepository.findAll(name,pageable);
            }
    }

}
