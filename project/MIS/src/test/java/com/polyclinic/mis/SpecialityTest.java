package com.polyclinic.mis;

import com.polyclinic.mis.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@SpringBootTest
public class SpecialityTest {
    @Autowired
    DoctorRepository doctorRepository;
    @Test
    void getAllSpecialities(){
        var specialities = doctorRepository.findAllSpecialities();
        System.out.println(specialities.length);
    }
    @Test
    void dateTest(){
        Date birthDate = Date.valueOf("2000-02-01");
        String pattern = "yyyy+MM+dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(birthDate);
        System.out.println(date);
    }
}
