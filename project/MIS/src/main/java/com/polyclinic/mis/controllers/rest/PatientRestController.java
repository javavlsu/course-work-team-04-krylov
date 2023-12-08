package com.polyclinic.mis.controllers.rest;

import com.polyclinic.mis.config.JwtService;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.models.PolyclinicUser;
import com.polyclinic.mis.models.rest.CheckPatientRoleRequest;
import com.polyclinic.mis.models.rest.PatientRequest;
import com.polyclinic.mis.service.PatientService;
import com.polyclinic.mis.service.PolyclinicUserService;
import com.polyclinic.mis.service.impl.PatientServiceImpl;
import com.polyclinic.mis.service.rest.PatientRestService;
import com.polyclinic.mis.service.rest.PolyclinicUserRestService;
import com.polyclinic.mis.service.rest.impl.PatientRestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PatientRestController {
    @Autowired
    PatientRestServiceImpl patientRestService;
    @Autowired
    PolyclinicUserRestService polyclinicUserRestService;
    @Autowired
    PatientServiceImpl patientService;
    @Autowired
    JwtService jwtService;

    @PostMapping("/checkPatientRole")
    public Boolean checkPatientRole(@RequestBody CheckPatientRoleRequest checkPatientRoleRequest){
        String email = checkPatientRoleRequest.getEmail();
        Patient patient = patientRestService.getPatientFromEmail(email);
        if (patient == null){
            return false;
        }
        else {
            return true;
        }
    }
    @PostMapping("/setPatientRole")
    public Boolean setPatientRole(@RequestBody PatientRequest patientRequest){
//        System.out.println(patientRequest.getPatient().toString());
//        System.out.println(patientRequest.getEmail());
        Patient patient = patientRequest.getPatient();
        String email = patientRequest.getEmail();
        try {
            PolyclinicUser polyclinicUser = polyclinicUserRestService.getPolyclinicUserByEmail(patientRequest.getEmail());
            System.out.println(polyclinicUser);
            patient.setUser(polyclinicUser);

            patientService.add(patient);
            patientRestService.assignRole(patient, polyclinicUser);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    @PostMapping("/getPatientCard")
    public Patient getPatientCard(@RequestHeader (name = "Authorization") String token){
//        System.out.println(jwtService.extractUsername(token.substring(7)));
        Patient patient = patientRestService.getPatientFromEmail(jwtService.extractUsername(token.substring(7)));
        return patient;
    }
}
