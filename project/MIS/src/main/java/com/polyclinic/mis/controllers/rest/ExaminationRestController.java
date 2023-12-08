package com.polyclinic.mis.controllers.rest;

import com.polyclinic.mis.config.JwtService;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.Inspection;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.service.impl.ExaminationServiceImpl;
import com.polyclinic.mis.service.rest.impl.PatientRestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExaminationRestController {
    @Autowired
    PatientRestServiceImpl patientRestService;
    @Autowired
    JwtService jwtService;
    @Autowired
    ExaminationServiceImpl examinationService;
    @GetMapping("/examinations")
    public List<Examination> getAllPatientExaminations(@RequestHeader(value = "Authorization") String token){
        Patient patient = patientRestService.getPatientFromEmail(jwtService.extractUsername(token.substring(7)));
        List<Examination> examinations = examinationService.getPatientExaminations(patient);
        return examinations;
    }
    @GetMapping("/examinations/{id}")
    public Examination getPatientExamination(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
//        Patient patient = patientRestService.getPatientFromEmail(jwtService.extractUsername(token.substring(7)));
        Examination examination = examinationService.getById(id).get();
        return examination;
    }
}
