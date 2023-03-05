package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Inspection;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.InspectionRepository;
import com.polyclinic.mis.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
