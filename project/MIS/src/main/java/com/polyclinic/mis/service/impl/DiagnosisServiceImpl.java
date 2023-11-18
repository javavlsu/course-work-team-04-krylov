package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Diagnosis;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.DiagnosisRepository;
import com.polyclinic.mis.service.DiagnosisService;
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
public class DiagnosisServiceImpl implements DiagnosisService {
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Override
    public Diagnosis add(Diagnosis diagnosis) {
        return diagnosisRepository.saveAndFlush(diagnosis);
    }

    @Override
    public Optional<Diagnosis> getById(String id) {
        return diagnosisRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        diagnosisRepository.deleteById(id);
    }

    @Override
    public List<Diagnosis> findByDescriptionContaining(String description) {
        return diagnosisRepository.findByDescriptionContaining(description);
    }

    @Override
    public Diagnosis edit(Diagnosis diagnosis) {
        return diagnosisRepository.saveAndFlush(diagnosis);
    }

    @Override
    public List<Diagnosis> getAll() {
        return diagnosisRepository.findAll();
    }


    @Override
    public Page<Diagnosis> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String id, String description) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (description.equals("") && id.equals("")) {
            return diagnosisRepository.findAll(pageable);
        } else {
            return diagnosisRepository.findAll(id, description, pageable);
        }
    }
}
