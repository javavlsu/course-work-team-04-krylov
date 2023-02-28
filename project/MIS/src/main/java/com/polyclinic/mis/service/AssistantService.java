package com.polyclinic.mis.service;

import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Assistant;

import java.util.List;

public interface AssistantService {
    Assistant add(Assistant assistant);
    void delete(Long id);
    Assistant edit(Assistant assistant);
    List<Assistant> getAll();
}
