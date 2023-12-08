package com.polyclinic.mis.controllers.rest;

import com.polyclinic.mis.config.JwtService;
import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.Inspection;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.service.impl.InspectionServiceImpl;
import com.polyclinic.mis.service.rest.impl.PatientRestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InspectionRestController {
    @Autowired
    PatientRestServiceImpl patientRestService;
    @Autowired
    JwtService jwtService;
    @Autowired
    InspectionServiceImpl inspectionService;
    @GetMapping("/inspections")
    public List<Inspection> getAllPatientInspections(@RequestHeader(value = "Authorization") String token){
        Patient patient = patientRestService.getPatientFromEmail(jwtService.extractUsername(token.substring(7)));
        List<Inspection> inspections = inspectionService.getPatientInspections(patient);
        return inspections;
    }
    @GetMapping("/inspections/{id}")
    public Inspection getAllPatientInspections(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
//        Patient patient = patientRestService.getPatientFromEmail(jwtService.extractUsername(token.substring(7)));
        Inspection inspection = inspectionService.getById(id).get();
        return inspection;
    }
}
