package com.polyclinic.mis.service.rest;

import com.polyclinic.mis.models.PolyclinicUser;

public interface PolyclinicUserRestService {
    public PolyclinicUser getPolyclinicUserByEmail(String email);
}
