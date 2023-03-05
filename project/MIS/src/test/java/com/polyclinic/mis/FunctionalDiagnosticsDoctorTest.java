package com.polyclinic.mis;

import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.FunctionalDiagnosticsDoctor;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FunctionalDiagnosticsDoctorTest {
    @Autowired
    FunctionalDiagnosticsDoctorServiceImpl functionalDiagnosticsDoctorService;
    @Test
    void addAndReadFunctionalDiagnosticsDoctor(){
        Date birthDate = Date.valueOf("2000-02-01");
        FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = new FunctionalDiagnosticsDoctor("Игнат","Сергеев","Анатольевич",birthDate);
        FunctionalDiagnosticsDoctor createdFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.add(functionalDiagnosticsDoctor);
        Optional<FunctionalDiagnosticsDoctor> readFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.getById(createdFunctionalDiagnosticsDoctor.getId());
        assertThat(functionalDiagnosticsDoctor)
                .usingRecursiveComparison()
                .isEqualTo(readFunctionalDiagnosticsDoctor.get());
    }
    @Test
    void addAndDeleteFunctionalDiagnosticsDoctor(){
        Date birthDate = Date.valueOf("2000-02-01");
        FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = new FunctionalDiagnosticsDoctor("Игнат","Сергеев","Анатольевич",birthDate);
        FunctionalDiagnosticsDoctor createdFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.add(functionalDiagnosticsDoctor);
        Optional<FunctionalDiagnosticsDoctor> readFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.getById(createdFunctionalDiagnosticsDoctor.getId());
        assertThat(functionalDiagnosticsDoctor)
                .usingRecursiveComparison()
                .isEqualTo(readFunctionalDiagnosticsDoctor.get());
        functionalDiagnosticsDoctorService.delete(createdFunctionalDiagnosticsDoctor.getId());
        readFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.getById(createdFunctionalDiagnosticsDoctor.getId());
        Assertions.assertFalse(readFunctionalDiagnosticsDoctor.isPresent());
    }
    @Test
    void editFunctionalDiagnosticsDoctor(){
        Date birthDate = Date.valueOf("2000-02-01");
        FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = new FunctionalDiagnosticsDoctor("Игнат","Сергеев","Анатольевич",birthDate);
        FunctionalDiagnosticsDoctor createdFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.add(functionalDiagnosticsDoctor);
        Optional<FunctionalDiagnosticsDoctor> readDoctor = functionalDiagnosticsDoctorService.getById(createdFunctionalDiagnosticsDoctor.getId());
        assertThat(functionalDiagnosticsDoctor)
                .usingRecursiveComparison()
                .isEqualTo(readDoctor.get());
        functionalDiagnosticsDoctor.setFirstName("Петр");
        FunctionalDiagnosticsDoctor editedDoctor = functionalDiagnosticsDoctorService.edit(functionalDiagnosticsDoctor);
        Optional<FunctionalDiagnosticsDoctor> readEditedPatient = functionalDiagnosticsDoctorService.getById(editedDoctor.getId());
        assertThat(functionalDiagnosticsDoctor)
                .usingRecursiveComparison()
                .isEqualTo(readEditedPatient.get());
    }
}
