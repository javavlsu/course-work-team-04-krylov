package com.polyclinic.mis;

import com.polyclinic.mis.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpecialityTest {
    @Autowired
    DoctorRepository doctorRepository;
    @Test
    void getAllSpecialities(){
        var specialities = doctorRepository.getAllSpecialities();
        System.out.println(specialities.length);
    }
}
