package com.polyclinic.mis;

import com.polyclinic.mis.models.*;
import com.polyclinic.mis.service.ExaminationService;
import com.polyclinic.mis.service.impl.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ExaminatonReferralTest {
    @Autowired
    FunctionalDiagnosticsDoctorServiceImpl functionalDiagnosticsDoctorService;
    @Autowired
    ExaminationServiceImpl examinationService;
    @Autowired
    ExaminationReferralServiceImpl examinationReferralService;
    @Autowired
    DiagnosisServiceImpl diagnosisService;
    @Autowired
    DoctorServiceImpl doctorService;
    @Autowired
    PatientServiceImpl patientService;
    @Test
    void addAndReadAnalysisReferral(){
        Date birthDate = Date.valueOf("2001-04-02");
        Doctor doctor = new Doctor("Анатолий","Петров","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctor = doctorService.add(doctor);
        Optional<Doctor> readDoctor = doctorService.getById(createdDoctor.getId());
        assertThat(doctor)
                .usingRecursiveComparison()
                .isEqualTo(readDoctor.get());
        Patient patient = new Patient("Иван","Иванов","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = new FunctionalDiagnosticsDoctor("Виктор","Михайлов","Олегович",birthDate);
        FunctionalDiagnosticsDoctor createdFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.add(functionalDiagnosticsDoctor);
        Optional<FunctionalDiagnosticsDoctor> readFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.getById(createdFunctionalDiagnosticsDoctor.getId());
        assertThat(functionalDiagnosticsDoctor)
                .usingRecursiveComparison()
                .isEqualTo(readFunctionalDiagnosticsDoctor.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
//        Timestamp dateOfTaking = Timestamp.valueOf("2023-02-01 18:38:03");
        String dateStr = "2023-02-03 18:38";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateOfTaking = LocalDateTime.parse(dateStr, formatter);


        ExaminationReferral examinationReferral = new ExaminationReferral(readDiagnosis.get(),readDoctor.get(),readPatient.get(),"Клинико-диагностическая лаборатория",readFunctionalDiagnosticsDoctor.get(),"292","Выписано",dateOfTaking,"Кровь на сахар","Расписание");
        ExaminationReferral createdExaminationReferral = examinationReferralService.add(examinationReferral);
        Optional<ExaminationReferral> readExaminationReferral = examinationReferralService.getById(createdExaminationReferral.getId());
        assertThat(examinationReferral)
                .usingRecursiveComparison()
                .isEqualTo(readExaminationReferral.get());
    }
    @Test
    void addAndDeleteAnalysisReferral(){
        Date birthDate = Date.valueOf("2002-03-02");
        Doctor doctor = new Doctor("Анатолий","Горбачев","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctor = doctorService.add(doctor);
        Optional<Doctor> readDoctor = doctorService.getById(createdDoctor.getId());
        assertThat(doctor)
                .usingRecursiveComparison()
                .isEqualTo(readDoctor.get());
        Patient patient = new Patient("Иван","Скворцов","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = new FunctionalDiagnosticsDoctor("Виктор","Савельев","Олегович",birthDate);
        FunctionalDiagnosticsDoctor createdFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.add(functionalDiagnosticsDoctor);
        Optional<FunctionalDiagnosticsDoctor> readFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.getById(createdFunctionalDiagnosticsDoctor.getId());
        assertThat(functionalDiagnosticsDoctor)
                .usingRecursiveComparison()
                .isEqualTo(readFunctionalDiagnosticsDoctor.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
//        Timestamp dateOfTaking = Timestamp.valueOf("2023-02-01 18:38:03");
        String dateStr = "2022-03-03 18:38";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateOfTaking = LocalDateTime.parse(dateStr, formatter);


        ExaminationReferral examinationReferral = new ExaminationReferral(readDiagnosis.get(),readDoctor.get(),readPatient.get(),"Клинико-диагностическая лаборатория",readFunctionalDiagnosticsDoctor.get(),"292","Выписано",dateOfTaking,"Кровь на сахар","Расписание");
        ExaminationReferral createdExaminationReferral = examinationReferralService.add(examinationReferral);
        Optional<ExaminationReferral> readExaminationReferral = examinationReferralService.getById(createdExaminationReferral.getId());
        assertThat(examinationReferral)
                .usingRecursiveComparison()
                .isEqualTo(readExaminationReferral.get());

        examinationReferralService.delete(createdExaminationReferral.getId());
        readExaminationReferral = examinationReferralService.getById(createdExaminationReferral.getId());
        Assertions.assertFalse(readExaminationReferral.isPresent());
    }
    @Test
    void editAnalysisReferral(){
        Date birthDate = Date.valueOf("2002-03-02");
        Doctor doctor = new Doctor("Анатолий","Горбачев","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctor = doctorService.add(doctor);
        Optional<Doctor> readDoctor = doctorService.getById(createdDoctor.getId());
        assertThat(doctor)
                .usingRecursiveComparison()
                .isEqualTo(readDoctor.get());
        Patient patient = new Patient("Иван","Скворцов","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor = new FunctionalDiagnosticsDoctor("Виктор","Савельев","Олегович",birthDate);
        FunctionalDiagnosticsDoctor createdFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.add(functionalDiagnosticsDoctor);
        Optional<FunctionalDiagnosticsDoctor> readFunctionalDiagnosticsDoctor = functionalDiagnosticsDoctorService.getById(createdFunctionalDiagnosticsDoctor.getId());
        assertThat(functionalDiagnosticsDoctor)
                .usingRecursiveComparison()
                .isEqualTo(readFunctionalDiagnosticsDoctor.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
//        Timestamp dateOfTaking = Timestamp.valueOf("2023-02-01 18:38:03");
        String dateStr = "2022-03-03 18:38";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateOfTaking = LocalDateTime.parse(dateStr, formatter);


        ExaminationReferral examinationReferral = new ExaminationReferral(readDiagnosis.get(),readDoctor.get(),readPatient.get(),"Клинико-диагностическая лаборатория",readFunctionalDiagnosticsDoctor.get(),"292","Выписано",dateOfTaking,"Кровь на сахар","Расписание");
        ExaminationReferral createdExaminationReferral = examinationReferralService.add(examinationReferral);
        Optional<ExaminationReferral> readExaminationReferral = examinationReferralService.getById(createdExaminationReferral.getId());
        assertThat(examinationReferral)
                .usingRecursiveComparison()
                .isEqualTo(readExaminationReferral.get());


        examinationReferral.setCabinetNum("575");
        ExaminationReferral editedExaminationReferral = examinationReferralService.edit(examinationReferral);
        Optional<ExaminationReferral> readEditedExaminationReferral = examinationReferralService.getById(editedExaminationReferral.getId());
        assertThat(examinationReferral)
                .usingRecursiveComparison()
                .isEqualTo(readEditedExaminationReferral.get());
    }
}
