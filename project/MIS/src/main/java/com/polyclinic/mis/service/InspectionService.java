package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Inspection;
import com.polyclinic.mis.models.Receptionist;

import java.util.List;
import java.util.Optional;

public interface InspectionService {
    Inspection add(Inspection inspection);
    Optional<Inspection> getById(Long id);
    void delete(Long id);
    Inspection edit(Inspection inspection);
    List<Inspection> getAll();

}
