package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.repository.AssistantRepository;
import com.polyclinic.mis.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssistantServiceImpl implements AssistantService {
    @Autowired
    private AssistantRepository assistantRepository;
    @Override
    public Assistant add(Assistant assistant) {
        return assistantRepository.saveAndFlush(assistant);
    }

    @Override
    public void delete(Long id) {
        assistantRepository.deleteById(id);
    }

    @Override
    public Assistant edit(Assistant assistant) {
        return assistantRepository.saveAndFlush(assistant);
    }

    @Override
    public List<Assistant> getAll() {
        return assistantRepository.findAll();
    }
}
