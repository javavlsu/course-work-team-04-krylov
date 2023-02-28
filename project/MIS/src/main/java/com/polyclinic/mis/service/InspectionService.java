package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Inspection;

import java.util.List;

public interface InspectionService {
    Inspection add(Inspection inspection);
    void delete(Long id);
    Inspection edit(Inspection inspection);
    List<Inspection> getAll();

}
