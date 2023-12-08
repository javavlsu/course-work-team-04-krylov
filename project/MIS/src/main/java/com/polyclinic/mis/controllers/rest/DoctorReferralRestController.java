package com.polyclinic.mis.controllers.rest;

import com.polyclinic.mis.config.JwtService;
import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.DoctorReferral;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.service.impl.DoctorReferralServiceImpl;
import com.polyclinic.mis.service.rest.impl.PatientRestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DoctorReferralRestController {
    @Autowired
    PatientRestServiceImpl patientRestService;
    @Autowired
    JwtService jwtService;
    @Autowired
    DoctorReferralServiceImpl doctorReferralService;
    @GetMapping("/doctorReferrals")
    public List<DoctorReferral> getAllPatientDoctorReferrals(@RequestHeader(value = "Authorization") String token){
        Patient patient = patientRestService.getPatientFromEmail(jwtService.extractUsername(token.substring(7)));
        List<DoctorReferral> doctorReferrals = doctorReferralService.getPatientDoctorReferrals(patient);
        return doctorReferrals;
    }
    @GetMapping("/doctorReferrals/{id}")
    public DoctorReferral getPatientDoctorReferral(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
//        Patient patient = patientRestService.getPatientFromEmail(jwtService.extractUsername(token.substring(7)));
        DoctorReferral doctorReferral = doctorReferralService.getById(id).get();
        return doctorReferral;
    }
}
