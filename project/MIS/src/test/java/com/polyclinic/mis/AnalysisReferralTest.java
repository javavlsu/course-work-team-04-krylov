package com.polyclinic.mis;

import com.polyclinic.mis.models.*;
import com.polyclinic.mis.service.DiagnosisService;
import com.polyclinic.mis.service.impl.*;
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
public class AnalysisReferralTest {
    @Autowired
    AssistantServiceImpl assistantService;
    @Autowired
    AnalysisServiceImpl analysisService;
    @Autowired
    AnalysisReferralServiceImpl analysisReferralService;
    @Autowired
    DiagnosisServiceImpl diagnosisService;
    @Autowired
    DoctorServiceImpl doctorService;
    @Autowired
    PatientServiceImpl patientService;
    @Test
    void addAndReadAnalysisReferral(){
        Date birthDate = Date.valueOf("2000-02-01");
        Doctor doctor = new Doctor("Василий","Иванович","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctor = doctorService.add(doctor);
        Optional<Doctor> readDoctor = doctorService.getById(createdDoctor.getId());
        assertThat(doctor)
                .usingRecursiveComparison()
                .isEqualTo(readDoctor.get());
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        Assistant assistant = new Assistant("Виктор","Михайлов","Олегович",birthDate);
        Assistant createdAssistant = assistantService.add(assistant);
        Optional<Assistant> readAssistant = assistantService.getById(createdAssistant.getId());
        assertThat(assistant)
                .usingRecursiveComparison()
                .isEqualTo(readAssistant.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
        Timestamp dateOfTaking = Timestamp.valueOf("2023-02-01 18:38:03");
        AnalysisReferral analysisReferral = new AnalysisReferral(readDiagnosis.get(),readDoctor.get(),readPatient.get(),"Клинико-диагностическая лаборатория",readAssistant.get(),"292","Выписано",dateOfTaking,"Кровь на сахар","Расписание");
        AnalysisReferral createdAnalysisReferral = analysisReferralService.add(analysisReferral);
        Optional<AnalysisReferral> readAnalysisReferral = analysisReferralService.getById(createdAnalysisReferral.getId());
        assertThat(analysisReferral)
                .usingRecursiveComparison()
                .isEqualTo(readAnalysisReferral.get());
    }
    @Test
    void addAndDeleteAnalysisReferral(){
        Date birthDate = Date.valueOf("2000-02-01");
        Doctor doctor = new Doctor("Василий","Иванович","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctor = doctorService.add(doctor);
        Optional<Doctor> readDoctor = doctorService.getById(createdDoctor.getId());
        assertThat(doctor)
                .usingRecursiveComparison()
                .isEqualTo(readDoctor.get());
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        Assistant assistant = new Assistant("Виктор","Михайлов","Олегович",birthDate);
        Assistant createdAssistant = assistantService.add(assistant);
        Optional<Assistant> readAssistant = assistantService.getById(createdAssistant.getId());
        assertThat(assistant)
                .usingRecursiveComparison()
                .isEqualTo(readAssistant.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
        Timestamp dateOfTaking = Timestamp.valueOf("2023-02-01 18:38:03");
        AnalysisReferral analysisReferral = new AnalysisReferral(readDiagnosis.get(),readDoctor.get(),readPatient.get(),"Клинико-диагностическая лаборатория",readAssistant.get(),"292","Выписано",dateOfTaking,"Кровь на сахар","Расписание");
        AnalysisReferral createdAnalysisReferral = analysisReferralService.add(analysisReferral);
        Optional<AnalysisReferral> readAnalysisReferral = analysisReferralService.getById(createdAnalysisReferral.getId());
        assertThat(analysisReferral)
                .usingRecursiveComparison()
                .isEqualTo(readAnalysisReferral.get());

        analysisReferralService.delete(createdAnalysisReferral.getId());
        readAnalysisReferral = analysisReferralService.getById(createdAnalysisReferral.getId());
        Assertions.assertFalse(readAnalysisReferral.isPresent());
    }
    @Test
    void editAnalysisReferral(){
        Date birthDate = Date.valueOf("2000-02-01");
        Doctor doctor = new Doctor("Василий","Иванович","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctor = doctorService.add(doctor);
        Optional<Doctor> readDoctor = doctorService.getById(createdDoctor.getId());
        assertThat(doctor)
                .usingRecursiveComparison()
                .isEqualTo(readDoctor.get());
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        Assistant assistant = new Assistant("Виктор","Михайлов","Олегович",birthDate);
        Assistant createdAssistant = assistantService.add(assistant);
        Optional<Assistant> readAssistant = assistantService.getById(createdAssistant.getId());
        assertThat(assistant)
                .usingRecursiveComparison()
                .isEqualTo(readAssistant.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
        Timestamp dateOfTaking = Timestamp.valueOf("2023-02-01 18:38:03");
        AnalysisReferral analysisReferral = new AnalysisReferral(readDiagnosis.get(),readDoctor.get(),readPatient.get(),"Клинико-диагностическая лаборатория",readAssistant.get(),"292","Выписано",dateOfTaking,"Кровь на сахар","Расписание");
        AnalysisReferral createdAnalysisReferral = analysisReferralService.add(analysisReferral);
        Optional<AnalysisReferral> readAnalysisReferral = analysisReferralService.getById(createdAnalysisReferral.getId());
        assertThat(analysisReferral)
                .usingRecursiveComparison()
                .isEqualTo(readAnalysisReferral.get());

        analysisReferral.setCabinetNum("575");
        AnalysisReferral editedAnalysisReferral = analysisReferralService.edit(analysisReferral);
        Optional<AnalysisReferral> readEditedAnalysisReferral = analysisReferralService.getById(editedAnalysisReferral.getId());
        assertThat(analysisReferral)
                .usingRecursiveComparison()
                .isEqualTo(readEditedAnalysisReferral.get());
    }
}
