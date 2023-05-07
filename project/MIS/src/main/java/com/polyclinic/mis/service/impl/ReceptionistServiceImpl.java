package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.models.Receptionist;
import com.polyclinic.mis.repository.ReceptionistRepository;
import com.polyclinic.mis.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceptionistServiceImpl implements ReceptionistService {
    @Autowired
    private ReceptionistRepository receptionistRepository;
    @Override
    public Receptionist add(Receptionist receptionist) {
        return receptionistRepository.saveAndFlush(receptionist);
    }
    @Override
    public Optional<Receptionist> getById(Long id){
        return receptionistRepository.findById(id);
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

    @Override
    public Page<Receptionist> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (birthDate.equals("")&&fio.equals("")){
            return receptionistRepository.findAll(pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return receptionistRepository.findAll(splitFio[0],"","","",pageable);
                case 2:
                    return receptionistRepository.findAll(splitFio[0],splitFio[1],"","",pageable);
                case 3:
                    return receptionistRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return receptionistRepository.findAll(splitFio[0],"","",birthDate,pageable);
                case 2:
                    return receptionistRepository.findAll(splitFio[0],splitFio[1],"",birthDate,pageable);
                case 3:
                    return receptionistRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,pageable);
            }
        }
        else {
            return receptionistRepository.findAll("","","",birthDate,pageable);
        }
        return receptionistRepository.findAll(pageable);
    }
}
