package com.polyclinic.mis.models.rest;

import com.polyclinic.mis.models.Patient;
import lombok.Data;

@Data
public class PatientRequest {
    Patient patient;
    String email;

}
