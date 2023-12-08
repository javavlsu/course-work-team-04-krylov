package com.polyclinic.mis.controllers.rest;


import com.polyclinic.mis.config.JwtService;
import com.polyclinic.mis.models.DoctorReferral;
import com.polyclinic.mis.models.ExaminationReferral;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.service.impl.ExaminationReferralServiceImpl;
import com.polyclinic.mis.service.rest.impl.PatientRestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExaminationReferralRestController {
    @Autowired
    PatientRestServiceImpl patientRestService;
    @Autowired
    JwtService jwtService;
    @Autowired
    ExaminationReferralServiceImpl examinationReferralService;

    @GetMapping("/examinationReferrals")
    public List<ExaminationReferral> getAllPatientDoctorReferrals(@RequestHeader(value = "Authorization") String token) {
        Patient patient = patientRestService.getPatientFromEmail(jwtService.extractUsername(token.substring(7)));
        List<ExaminationReferral> patientDoctorReferrals = examinationReferralService.getPatientExaminationReferrals(patient);
        return patientDoctorReferrals;
    }

    @GetMapping("/examinationReferrals/{id}")
    public ExaminationReferral getPatientDoctorReferral(@RequestHeader(value = "Authorization") String token, @PathVariable long id) {
//        Patient patient = patientRestService.getPatientFromEmail(jwtService.extractUsername(token.substring(7)));
        ExaminationReferral doctorReferral = examinationReferralService.getById(id).get();
        return doctorReferral;
    }
}
