package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.*;
import com.polyclinic.mis.repository.*;
import com.polyclinic.mis.service.PolyclinicUserService;
import com.polyclinic.mis.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PolyclinicUserServiceImpl implements PolyclinicUserService {
    @Autowired
    private PolyclinicUserRepository polyclinicUserRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AssistantRepository assistantRepository;

    @Autowired
    private FunctionalDiagnosticsDoctorRepository functionalDiagnosticsDoctorRepository;
    @Override
    public PolyclinicUser add(PolyclinicUser polyclinicUser) {
        return polyclinicUserRepository.saveAndFlush(polyclinicUser);
    }
    @Override
    public Optional<PolyclinicUser> getById(Long id){
        return polyclinicUserRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        polyclinicUserRepository.deleteById(id);
    }

    @Override
    public PolyclinicUser edit(PolyclinicUser polyclinicUser) {
        return polyclinicUserRepository.saveAndFlush(polyclinicUser);
    }

    @Override
    public List<PolyclinicUser> getAll() {
        return polyclinicUserRepository.findAll();
    }

    @Override
    public PolyclinicUser getByEmail(String email) {
        return polyclinicUserRepository.findByEmail(email).get();
    }

    public PolyclinicUser getUserFromContext(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        PolyclinicUser polyclinicUser = getByEmail(userEmail);
        return polyclinicUser;
    }
    public Patient getPatientFromContext(){
        PolyclinicUser user = getUserFromContext();
        Patient patient = patientRepository.findByUserId(user.getId()).get();
        return patient;
    }

    public Doctor getDoctorFromContext(){
        PolyclinicUser user = getUserFromContext();
        Doctor doctor = doctorRepository.findByUserId(user.getId()).get();
        return doctor;
    }

    public Assistant getAssistantFromContext(){
        PolyclinicUser user = getUserFromContext();
        Assistant assistant = assistantRepository.findByUserId(user.getId()).get();
        return assistant;
    }

    public FunctionalDiagnosticsDoctor getFunctionalDiagnosticsDoctorFromContext(){
        PolyclinicUser user = getUserFromContext();
        FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = functionalDiagnosticsDoctorRepository.findByUserId(user.getId()).get();
        return functionalDiagnosticsDoctor;
    }


//    @Override
//    public Page<Receptionist> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
//        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
//        if (birthDate.equals("")&&fio.equals("")){
//            return receptionistRepository.findAll(pageable);
//        }
//        else if (birthDate.equals("")){
//            String[] splitFio = fio.split(" ");
//            switch (splitFio.length){
//                case 1:
//                    return receptionistRepository.findAll(splitFio[0],"","","",pageable);
//                case 2:
//                    return receptionistRepository.findAll(splitFio[0],splitFio[1],"","",pageable);
//                case 3:
//                    return receptionistRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",pageable);
//            }
//        }
//        else if (!fio.equals("")){
//
//            String[] splitFio = fio.split(" ");
//            switch (splitFio.length){
//                case 1:
//                    return receptionistRepository.findAll(splitFio[0],"","",birthDate,pageable);
//                case 2:
//                    return receptionistRepository.findAll(splitFio[0],splitFio[1],"",birthDate,pageable);
//                case 3:
//                    return receptionistRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,pageable);
//            }
//        }
//        else {
//            return receptionistRepository.findAll("","","",birthDate,pageable);
//        }
//        return receptionistRepository.findAll(pageable);
//    }
}
