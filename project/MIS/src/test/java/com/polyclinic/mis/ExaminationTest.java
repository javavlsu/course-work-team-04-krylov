package com.polyclinic.mis;

import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.FunctionalDiagnosticsDoctor;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.service.impl.ExaminationReferralServiceImpl;
import com.polyclinic.mis.service.impl.ExaminationServiceImpl;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import com.polyclinic.mis.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ExaminationTest {
    @Autowired
    FunctionalDiagnosticsDoctorServiceImpl functionalDiagnosticsDoctorService;
    @Autowired
    ExaminationServiceImpl examinationService;
    @Autowired
    PatientServiceImpl patientService;
    @Test
    void addAndReadExamination(){
        Date birthDate = Date.valueOf("2000-02-01");
        FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = new FunctionalDiagnosticsDoctor("Игнат","Сергеев","Анатольевич",birthDate);
        FunctionalDiagnosticsDoctor createdFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.add(functionalDiagnosticsDoctor);
        Optional<FunctionalDiagnosticsDoctor> readFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.getById(createdFunctionalDiagnosticsDoctor.getId());
        assertThat(functionalDiagnosticsDoctor)
                .usingRecursiveComparison()
                .isEqualTo(readFunctionalDiagnosticsDoctor.get());
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        Timestamp date = Timestamp.valueOf("2023-02-01 18:38:03");

        Examination examination = new Examination("Электрокардиограмма","Заключение1",date,readFunctionalDiagnosticsDoctor.get(),readPatient.get());
        Examination createdExamination = examinationService.add(examination);
        Optional<Examination> readExamination = examinationService.getById(createdExamination.getId());
        assertThat(examination)
                .usingRecursiveComparison()
                .isEqualTo(readExamination.get());
    }
    @Test
    void addAndDeleteExamination(){
        Date birthDate = Date.valueOf("2000-02-01");
        FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = new FunctionalDiagnosticsDoctor("Игнат","Сергеев","Анатольевич",birthDate);
        FunctionalDiagnosticsDoctor createdFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.add(functionalDiagnosticsDoctor);
        Optional<FunctionalDiagnosticsDoctor> readFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.getById(createdFunctionalDiagnosticsDoctor.getId());
        assertThat(functionalDiagnosticsDoctor)
                .usingRecursiveComparison()
                .isEqualTo(readFunctionalDiagnosticsDoctor.get());
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        Timestamp date = Timestamp.valueOf("2023-02-01 18:38:03");

        Examination examination = new Examination("Электрокардиограмма","Заключение1",date,readFunctionalDiagnosticsDoctor.get(),readPatient.get());
        Examination createdExamination = examinationService.add(examination);
        Optional<Examination> readExamination = examinationService.getById(createdExamination.getId());
        assertThat(examination)
                .usingRecursiveComparison()
                .isEqualTo(readExamination.get());

        examinationService.delete(createdExamination.getId());
        readExamination = examinationService.getById(createdExamination.getId());
        Assertions.assertFalse(readExamination.isPresent());
    }
    @Test
    void editExamination(){
        Date birthDate = Date.valueOf("2000-02-01");
        FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = new FunctionalDiagnosticsDoctor("Игнат","Сергеев","Анатольевич",birthDate);
        FunctionalDiagnosticsDoctor createdFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.add(functionalDiagnosticsDoctor);
        Optional<FunctionalDiagnosticsDoctor> readFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.getById(createdFunctionalDiagnosticsDoctor.getId());
        assertThat(functionalDiagnosticsDoctor)
                .usingRecursiveComparison()
                .isEqualTo(readFunctionalDiagnosticsDoctor.get());
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        Timestamp date = Timestamp.valueOf("2023-02-01 18:38:03");

        Examination examination = new Examination("Электрокардиограмма","Заключение1",date,readFunctionalDiagnosticsDoctor.get(),readPatient.get());
        Examination createdExamination = examinationService.add(examination);
        Optional<Examination> readExamination = examinationService.getById(createdExamination.getId());
        assertThat(examination)
                .usingRecursiveComparison()
                .isEqualTo(readExamination.get());
        examination.setReport("Заключение2");
        Examination editedExamination = examinationService.edit(examination);
        Optional<Examination> readEditedExamination = examinationService.getById(editedExamination.getId());
        assertThat(editedExamination)
                .usingRecursiveComparison()
                .isEqualTo(readEditedExamination.get());
    }
}
