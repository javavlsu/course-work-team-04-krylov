package com.polyclinic.mis;

import com.polyclinic.mis.models.Diagnosis;
import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.DoctorReferral;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.service.impl.DiagnosisServiceImpl;
import com.polyclinic.mis.service.impl.DoctorReferralServiceImpl;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
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
public class DoctorReferralTest {
    @Autowired
    DoctorServiceImpl doctorService;
    @Autowired
    DoctorReferralServiceImpl doctorReferralService;
    @Autowired
    DiagnosisServiceImpl diagnosisService;
    @Autowired
    PatientServiceImpl patientService;
    @Test
    void addAndReadDoctorReferral(){
        Date birthDate = Date.valueOf("2000-02-01");
        Doctor doctorInitial = new Doctor("Василий","Иванович","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctorInitial = doctorService.add(doctorInitial);
        Optional<Doctor> readDoctorInitial = doctorService.getById(createdDoctorInitial.getId());
        assertThat(doctorInitial)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorInitial.get());
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        Doctor doctorTarget = new Doctor("Петр","Иванович","Альбертович",birthDate,"Невролог","Высшая категория","Доктор наук");
        Doctor createdDoctorTarget = doctorService.add(doctorTarget);
        Optional<Doctor> readDoctorTarget = doctorService.getById(createdDoctorTarget.getId());
        assertThat(doctorTarget)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorTarget.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
        Timestamp date = Timestamp.valueOf("2023-02-01 18:38:03");

        DoctorReferral doctorReferral = new DoctorReferral(readDiagnosis.get(),readDoctorInitial.get(),readPatient.get(),readDoctorTarget.get(),"676","Выписано",date,"Нервы кистей рук","Расписание");
        DoctorReferral createdDoctorReferral = doctorReferralService.add(doctorReferral);
        Optional<DoctorReferral> readDoctorReferral = doctorReferralService.getById(createdDoctorReferral.getId());
        assertThat(doctorReferral)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorReferral.get());

    }

    @Test
    void addAndDeleteDoctorReferral(){
        Date birthDate = Date.valueOf("2000-02-01");
        Doctor doctorInitial = new Doctor("Василий","Иванович","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctorInitial = doctorService.add(doctorInitial);
        Optional<Doctor> readDoctorInitial = doctorService.getById(createdDoctorInitial.getId());
        assertThat(doctorInitial)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorInitial.get());
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        Doctor doctorTarget = new Doctor("Петр","Иванович","Альбертович",birthDate,"Невролог","Высшая категория","Доктор наук");
        Doctor createdDoctorTarget = doctorService.add(doctorTarget);
        Optional<Doctor> readDoctorTarget = doctorService.getById(createdDoctorTarget.getId());
        assertThat(doctorTarget)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorTarget.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
        Timestamp date = Timestamp.valueOf("2023-02-01 18:38:03");

        DoctorReferral doctorReferral = new DoctorReferral(readDiagnosis.get(),readDoctorInitial.get(),readPatient.get(),readDoctorTarget.get(),"676","Выписано",date,"Нервы кистей рук","Расписаниев");
        DoctorReferral createdDoctorReferral = doctorReferralService.add(doctorReferral);
        Optional<DoctorReferral> readDoctorReferral = doctorReferralService.getById(createdDoctorReferral.getId());
        assertThat(doctorReferral)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorReferral.get());

        doctorReferralService.delete(createdDoctorReferral.getId());
        readDoctorReferral = doctorReferralService.getById(createdDoctorReferral.getId());
        Assertions.assertFalse(readDoctorReferral.isPresent());
    }
    @Test
    void editDoctorReferral(){
        Date birthDate = Date.valueOf("2000-02-01");
        Doctor doctorInitial = new Doctor("Василий","Иванович","Альбертович",birthDate,"Терапевт","Высшая категория","Доктор наук");
        Doctor createdDoctorInitial = doctorService.add(doctorInitial);
        Optional<Doctor> readDoctorInitial = doctorService.getById(createdDoctorInitial.getId());
        assertThat(doctorInitial)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorInitial.get());
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,124563,"Компания1",123976,"Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        Doctor doctorTarget = new Doctor("Петр","Иванович","Альбертович",birthDate,"Невролог","Высшая категория","Доктор наук");
        Doctor createdDoctorTarget = doctorService.add(doctorTarget);
        Optional<Doctor> readDoctorTarget = doctorService.getById(createdDoctorTarget.getId());
        assertThat(doctorTarget)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorTarget.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
        Timestamp date = Timestamp.valueOf("2023-02-01 18:38:03");

        DoctorReferral doctorReferral = new DoctorReferral(readDiagnosis.get(),readDoctorInitial.get(),readPatient.get(),readDoctorTarget.get(),"676","Выписано",date,"Нервы кистей рук","Расписаниев");
        DoctorReferral createdDoctorReferral = doctorReferralService.add(doctorReferral);
        Optional<DoctorReferral> readDoctorReferral = doctorReferralService.getById(createdDoctorReferral.getId());
        assertThat(doctorReferral)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorReferral.get());
        doctorReferral.setCabinetNum("643");
        DoctorReferral editedDoctorReferral = doctorReferralService.edit(doctorReferral);
        Optional<DoctorReferral> readEditedDoctorReferral = doctorReferralService.getById(editedDoctorReferral.getId());
        assertThat(doctorReferral)
                .usingRecursiveComparison()
                .isEqualTo(readEditedDoctorReferral.get());
    }

}
