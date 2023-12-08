package com.polyclinic.mis.service.rest.impl;

import com.polyclinic.mis.models.PolyclinicUser;
import com.polyclinic.mis.repository.PolyclinicUserRepository;
import com.polyclinic.mis.service.rest.PolyclinicUserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PolyclinicUserRestServiceImpl implements PolyclinicUserRestService {
    @Autowired
    PolyclinicUserRepository polyclinicUserRepository;
    @Override
    public PolyclinicUser getPolyclinicUserByEmail(String email) {
        //todo Возвращать null если нет такого пользователя
        System.out.println(email);
        return polyclinicUserRepository.findByEmail(email).get();
    }
}
