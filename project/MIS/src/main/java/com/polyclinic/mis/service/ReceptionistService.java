package com.polyclinic.mis.service;

import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.Diagnosis;
import com.polyclinic.mis.models.Receptionist;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ReceptionistService {

    Receptionist add(Receptionist receptionist);

    Optional<Receptionist> getById(Long id);
    void delete(Long id);
    Receptionist edit(Receptionist receptionist);
    List<Receptionist> getAll();

    Page<Receptionist> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate);

}
