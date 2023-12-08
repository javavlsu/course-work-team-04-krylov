package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.models.PolyclinicUser;
import com.polyclinic.mis.repository.PatientRepository;
import com.polyclinic.mis.repository.RoleRepository;
import com.polyclinic.mis.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PolyclinicUserServiceImpl polyclinicUserService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Override
    public Patient add(Patient patient) {
        if (patient.getPolisEndDate()!=null&&patient.getPolisEndDateString()!="")
        {
            patient.setPolisEndDate(Date.valueOf(patient.getPolisEndDateString()));
        }
        return patientRepository.saveAndFlush(patient);
    }
    @Override
    public Optional<Patient> getById(Long id){
        return patientRepository.findById(id);
    }

    public Optional<Patient> getByEmail(String email){
        PolyclinicUser polyclinicUser = polyclinicUserService.getByEmail(email);
        return patientRepository.findByUserId(polyclinicUser.getId());
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    public void update(Patient patient){
        patientRepository.saveAndFlush(patient);
    }

    @Override
    public Patient edit(Patient patient) {
        return patientRepository.saveAndFlush(patient);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Page<Patient> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (birthDate.equals("")&&fio.equals("")){
            return patientRepository.findAll(pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return patientRepository.findAll(splitFio[0],"","","",pageable);
                case 2:
                    return patientRepository.findAll(splitFio[0],splitFio[1],"","",pageable);
                case 3:
                    return patientRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return patientRepository.findAll(splitFio[0],"","",birthDate,pageable);
                case 2:
                    return patientRepository.findAll(splitFio[0],splitFio[1],"",birthDate,pageable);
                case 3:
                    return patientRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,pageable);
            }
        }
        else {
            return patientRepository.findAll("","","",birthDate,pageable);
        }
        return patientRepository.findAll(pageable);
    }

    public Patient currentPatient(){
        return polyclinicUserService.getPatientFromContext();
    }
    public void assignRole(Patient patient, PolyclinicUser user){
        var patientUser= patient.getUser();
        var roles = user.getRoles();
        var canRegisterAsPatientRole = roleRepository.findByName("CanRegisterAsPatient").get();
        roles.remove(canRegisterAsPatientRole);
        var patientRole = roleRepository.findByName("Patient").get();
        roles.add(patientRole);
        patientUser.setRoles(roles);
        userService.updateUser(patientUser);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        updatedAuthorities.remove(new SimpleGrantedAuthority(canRegisterAsPatientRole.getName()));
        updatedAuthorities.add(new SimpleGrantedAuthority(patientRole.getName()));

        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
