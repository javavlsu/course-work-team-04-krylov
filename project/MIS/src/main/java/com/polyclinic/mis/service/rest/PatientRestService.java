package com.polyclinic.mis.service.rest;

import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.models.PolyclinicUser;

public interface PatientRestService {

    public Patient getPatientFromEmail(String email);

    public void assignRole(Patient patient, PolyclinicUser polyclinicUser);
}
