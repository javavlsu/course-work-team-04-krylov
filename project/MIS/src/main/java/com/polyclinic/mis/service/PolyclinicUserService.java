package com.polyclinic.mis.service;

import com.polyclinic.mis.models.PolyclinicUser;
import com.polyclinic.mis.models.Receptionist;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PolyclinicUserService {

    PolyclinicUser add(PolyclinicUser polyclinicUser);

    Optional<PolyclinicUser> getById(Long id);
    void delete(Long id);
    PolyclinicUser edit(PolyclinicUser polyclinicUser);
    List<PolyclinicUser> getAll();

    PolyclinicUser getByEmail(String email);

//    Page<Receptionist> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate);

}
