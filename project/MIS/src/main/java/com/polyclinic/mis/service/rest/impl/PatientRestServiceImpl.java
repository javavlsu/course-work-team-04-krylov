package com.polyclinic.mis.service.rest.impl;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.models.PolyclinicUser;
import com.polyclinic.mis.repository.PatientRepository;
import com.polyclinic.mis.repository.RoleRepository;
import com.polyclinic.mis.service.PatientService;
import com.polyclinic.mis.service.PolyclinicUserService;
import com.polyclinic.mis.service.impl.PatientServiceImpl;
import com.polyclinic.mis.service.impl.PolyclinicUserServiceImpl;
import com.polyclinic.mis.service.rest.PatientRestService;
import com.polyclinic.mis.service.rest.PolyclinicUserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientRestServiceImpl implements PatientRestService {

    @Autowired
    PolyclinicUserRestService polyclinicUserRestService;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    PatientServiceImpl patientService;
    @Override
    public Patient getPatientFromEmail(String email) {
        PolyclinicUser polyclinicUser = polyclinicUserRestService.getPolyclinicUserByEmail(email);
        Patient patient = patientRepository.findByUserId(polyclinicUser.getId()).orElse(null);
        //todo возвращать null если нет пациента
        return patient;
    }

    @Override
    public void assignRole(Patient patient, PolyclinicUser user) {
        var patientUser= patient.getUser();
//        patient.setUser(patientUser);
//        var patientUser= patient.getUser();
        var roles = user.getRoles();
        var canRegisterAsPatientRole = roleRepository.findByName("CanRegisterAsPatient").get();
        roles.remove(canRegisterAsPatientRole);
        var patientRole = roleRepository.findByName("Patient").get();
        roles.add(patientRole);
        patientUser.setRoles(roles);
        userService.updateUser(patientUser);
    }
}
