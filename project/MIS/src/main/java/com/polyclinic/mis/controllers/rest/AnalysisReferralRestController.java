package com.polyclinic.mis.controllers.rest;


import com.polyclinic.mis.config.JwtService;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.ExaminationReferral;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.rest.impl.PatientRestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnalysisReferralRestController {
    @Autowired
    PatientRestServiceImpl patientRestService;
    @Autowired
    JwtService jwtService;
    @Autowired
    AnalysisReferralServiceImpl analysisReferralService;

    @GetMapping("/analysisReferrals")
    public List<AnalysisReferral> getAllPatientAnalysisReferrals(@RequestHeader(value = "Authorization") String token) {
        Patient patient = patientRestService.getPatientFromEmail(jwtService.extractUsername(token.substring(7)));
        List<AnalysisReferral> patientAnalysisReferrals = analysisReferralService.getPatientAnalysisReferrals(patient);
        return patientAnalysisReferrals;
    }

    @GetMapping("/analysisReferrals/{id}")
    public AnalysisReferral getPatientAnalysisReferral(@RequestHeader(value = "Authorization") String token, @PathVariable long id) {
//        Patient patient = patientRestService.getPatientFromEmail(jwtService.extractUsername(token.substring(7)));
        AnalysisReferral doctorReferral = analysisReferralService.getById(id).get();
        return doctorReferral;
    }
}
