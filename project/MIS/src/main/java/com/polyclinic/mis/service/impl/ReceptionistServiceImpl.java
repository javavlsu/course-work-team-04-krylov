package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Receptionist;
import com.polyclinic.mis.repository.ReceptionistRepository;
import com.polyclinic.mis.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReceptionistServiceImpl implements ReceptionistService {
    @Autowired
    private ReceptionistRepository receptionistRepository;
    @Override
    public Receptionist add(Receptionist receptionist) {
        return receptionistRepository.saveAndFlush(receptionist);
    }

    @Override
    public void delete(Long id) {
        receptionistRepository.deleteById(id);
    }

    @Override
    public Receptionist edit(Receptionist receptionist) {
        return receptionistRepository.saveAndFlush(receptionist);
    }

    @Override
    public List<Receptionist> getAll() {
        return receptionistRepository.findAll();
    }
}
