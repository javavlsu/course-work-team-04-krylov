package com.polyclinic.mis.service;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.Receptionist;

import java.util.List;
import java.util.Optional;

public interface AssistantService {
    Assistant add(Assistant assistant);
    Optional<Assistant> getById(Long id);
    void delete(Long id);
    Assistant edit(Assistant assistant);
    List<Assistant> getAll();
}
