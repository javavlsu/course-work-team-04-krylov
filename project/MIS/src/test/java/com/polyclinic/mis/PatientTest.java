package com.polyclinic.mis;

import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PatientTest {
    @Autowired
    PatientServiceImpl patientService;
    @Test
    void addAndReadPatient(){
        Date birthDate = Date.valueOf("2000-02-01");
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
    }
    @Test
    void addAndDeletePatient(){
        Date birthDate = Date.valueOf("2000-02-01");
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        patientService.delete(createdPatient.getId());
        readPatient = patientService.getById(createdPatient.getId());
        Assertions.assertFalse(readPatient.isPresent());
    }
    @Test
    void editPatient(){
        Date birthDate = Date.valueOf("2000-02-01");
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        patient.setFirstName("Петр");
        Patient editedPatient = patientService.edit(patient);
        Optional<Patient> readEditedPatient = patientService.getById(editedPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readEditedPatient.get());
    }

}
