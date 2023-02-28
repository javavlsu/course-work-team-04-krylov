package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Examination;

import java.util.List;

public interface ExaminationService {
    Examination add(Examination examination);
    void delete(Long id);
    Examination edit(Examination examination);
    List<Examination> getAll();
}
