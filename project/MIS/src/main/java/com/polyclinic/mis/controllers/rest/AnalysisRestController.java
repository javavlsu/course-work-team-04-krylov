package com.polyclinic.mis.controllers.rest;

import com.polyclinic.mis.config.JwtService;
import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.service.impl.AnalysisServiceImpl;
import com.polyclinic.mis.service.rest.impl.PatientRestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnalysisRestController {
    @Autowired
    PatientRestServiceImpl patientRestService;
    @Autowired
    JwtService jwtService;
    @Autowired
    AnalysisServiceImpl analysisService;
    @GetMapping("/analyses")
    public List<Analysis> getAllPatientExaminations(@RequestHeader(value = "Authorization") String token){
        Patient patient = patientRestService.getPatientFromEmail(jwtService.extractUsername(token.substring(7)));
        List<Analysis> analyses = analysisService.getPatientAnalyses(patient);
        return analyses;
    }
    @GetMapping("/analyses/{id}")
    public Analysis getPatientExamination(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
//        Patient patient = patientRestService.getPatientFromEmail(jwtService.extractUsername(token.substring(7)));
        Analysis analysis = analysisService.getById(id).get();
        return analysis;
    }
}
