package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Receptionist;

import java.util.List;

public interface ReceptionistService {

    Receptionist add(Receptionist receptionist);
    void delete(Long id);
    Receptionist edit(Receptionist receptionist);
    List<Receptionist> getAll();
}
