package com.polyclinic.mis.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.AssistantRepository;
import com.polyclinic.mis.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AssistantServiceImpl implements AssistantService {
    @Autowired
    private AssistantRepository assistantRepository;

    @Autowired
    private PolyclinicUserServiceImpl polyclinicUserService;
    @Override
    public Assistant add(Assistant assistant) {
        return assistantRepository.saveAndFlush(assistant);
    }
    @Override
    public Optional<Assistant> getById(Long id){
        return assistantRepository.findById(id);
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

    @Override
    public Page<Assistant> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (birthDate.equals("")&&fio.equals("")){
            return assistantRepository.findAll(pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return assistantRepository.findAll(splitFio[0],"","","",pageable);
                case 2:
                    return assistantRepository.findAll(splitFio[0],splitFio[1],"","",pageable);
                case 3:
                    return assistantRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return assistantRepository.findAll(splitFio[0],"","",birthDate,pageable);
                case 2:
                    return assistantRepository.findAll(splitFio[0],splitFio[1],"",birthDate,pageable);
                case 3:
                    return assistantRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,pageable);
            }
        }
        else {
            return assistantRepository.findAll("","","",birthDate,pageable);
        }
        return assistantRepository.findAll(pageable);
    }
    public Assistant currentAssistant(){
        return polyclinicUserService.getAssistantFromContext();
    }

}
