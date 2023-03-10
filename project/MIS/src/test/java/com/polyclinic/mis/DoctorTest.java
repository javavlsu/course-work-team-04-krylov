package com.polyclinic.mis;

import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.repository.DoctorRepository;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
import com.polyclinic.mis.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class DoctorTest {
    @Autowired
    DoctorServiceImpl doctorService;
    @Test
    void addAndReadDoctor(){
        Date birthDate = Date.valueOf("2000-02-01");
        Doctor doctor = new Doctor("Василий","Иванович","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctor = doctorService.add(doctor);
        Optional<Doctor> readDoctor = doctorService.getById(createdDoctor.getId());
        assertThat(doctor)
                .usingRecursiveComparison()
                .isEqualTo(readDoctor.get());
    }
    @Test
    void addAndDeleteDoctor(){
        Date birthDate = Date.valueOf("2000-02-01");
        Doctor doctor = new Doctor("Василий","Иванович","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctor = doctorService.add(doctor);
        Optional<Doctor> readDoctor = doctorService.getById(createdDoctor.getId());
        assertThat(doctor)
                .usingRecursiveComparison()
                .isEqualTo(readDoctor.get());
        doctorService.delete(createdDoctor.getId());
        readDoctor = doctorService.getById(createdDoctor.getId());
        Assertions.assertFalse(readDoctor.isPresent());
    }
    @Test
    void editDoctor(){
        Date birthDate = Date.valueOf("2000-02-01");
        Doctor doctor = new Doctor("Василий","Иванович","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctor = doctorService.add(doctor);
        Optional<Doctor> readDoctor = doctorService.getById(createdDoctor.getId());
        assertThat(doctor)
                .usingRecursiveComparison()
                .isEqualTo(readDoctor.get());
        doctor.setFirstName("Петр");
        Doctor editedDoctor = doctorService.edit(doctor);
        Optional<Doctor> readEditedDoctor = doctorService.getById(editedDoctor.getId());
        assertThat(doctor)
                .usingRecursiveComparison()
                .isEqualTo(readEditedDoctor.get());
    }
}
