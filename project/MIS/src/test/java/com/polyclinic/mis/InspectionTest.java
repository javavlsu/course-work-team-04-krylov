package com.polyclinic.mis;

import com.polyclinic.mis.models.Diagnosis;
import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.Inspection;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.service.impl.DiagnosisServiceImpl;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
import com.polyclinic.mis.service.impl.InspectionServiceImpl;
import com.polyclinic.mis.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@SpringBootTest
public class InspectionTest {
    @Autowired
    PatientServiceImpl patientService;
    @Autowired
    DiagnosisServiceImpl diagnosisService;
    @Autowired
    DoctorServiceImpl doctorService;
    @Autowired
    InspectionServiceImpl inspectionService;
    @Test
    void addAndReadInspection(){
        Date birthDate = Date.valueOf("2000-02-01");
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,"124563","Компания1","123976","Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
        Doctor doctor = new Doctor("Василий","Иванов","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctor = doctorService.add(doctor);
        Optional<Doctor> readDoctor = doctorService.getById(createdDoctor.getId());
        assertThat(doctor)
                .usingRecursiveComparison()
                .isEqualTo(readDoctor.get());
        String dateStr = "2023-01-01 19:38";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateOfTaking = LocalDateTime.parse(dateStr, formatter);
        Inspection inspection = new Inspection(readPatient.get(),"Жалобы","Рецепт",readDiagnosis.get(),dateOfTaking,"Первичный осмотр",readDoctor.get());
        Inspection createdInspection = inspectionService.add(inspection);
        Optional<Inspection> readInspection = inspectionService.getById(createdInspection.getId());
        assertThat(inspection)
                .usingRecursiveComparison()
                .isEqualTo(readInspection.get());
    }
    @Test
    void addAndDeleteInspection(){
        Date birthDate = Date.valueOf("2000-02-01");
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,"124563","Компания1","123976","Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
        Doctor doctor = new Doctor("Василий","Иванов","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctor = doctorService.add(doctor);
        Optional<Doctor> readDoctor = doctorService.getById(createdDoctor.getId());
        assertThat(doctor)
                .usingRecursiveComparison()
                .isEqualTo(readDoctor.get());

        String dateStr = "2022-01-01 18:38";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateOfTaking = LocalDateTime.parse(dateStr, formatter);

        Inspection inspection = new Inspection(readPatient.get(),"Жалобы","Рецепт",readDiagnosis.get(),dateOfTaking,"Первичный осмотр",readDoctor.get());
        Inspection createdInspection = inspectionService.add(inspection);
        Optional<Inspection> readInspection = inspectionService.getById(createdInspection.getId());
        assertThat(inspection)
                .usingRecursiveComparison()
                .isEqualTo(readInspection.get());


        inspectionService.delete(createdInspection.getId());
        readInspection = inspectionService.getById(createdInspection.getId());
        Assertions.assertFalse(readInspection.isPresent());
    }
    @Test
    void editInspection(){
        Date birthDate = Date.valueOf("2001-03-01");
        Patient patient = new Patient("Петр","Иванович","Михайлович",birthDate,"124563","Компания1","123976","Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
        Doctor doctor = new Doctor("Василий","Скворцов","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctor = doctorService.add(doctor);
        Optional<Doctor> readDoctor = doctorService.getById(createdDoctor.getId());
        assertThat(doctor)
                .usingRecursiveComparison()
                .isEqualTo(readDoctor.get());
//        Timestamp dateOfTaking = Timestamp.valueOf("2023-03-01 18:38:03");

        String dateStr = "2023-01-01 18:38";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateOfTaking = LocalDateTime.parse(dateStr, formatter);

        Inspection inspection = new Inspection(readPatient.get(),"Жалобы","Рецепт",readDiagnosis.get(),dateOfTaking,"Первичный осмотр",readDoctor.get());
        Inspection createdInspection = inspectionService.add(inspection);
        Optional<Inspection> readInspection = inspectionService.getById(createdInspection.getId());
        assertThat(inspection)
                .usingRecursiveComparison()
                .isEqualTo(readInspection.get());

        inspection.setType("Повторный осмотр");
        Inspection editedInspection = inspectionService.edit(inspection);
        Optional<Inspection> readEditedInspection = inspectionService.getById(editedInspection.getId());
        assertThat(inspection)
                .usingRecursiveComparison()
                .isEqualTo(readEditedInspection.get());
    }

}
