package com.polyclinic.mis;

import com.polyclinic.mis.models.*;
import com.polyclinic.mis.service.impl.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DoctorAppointmentTest {
    @Autowired
    DoctorServiceImpl doctorService;
    @Autowired
    PatientServiceImpl patientService;
    @Autowired
    DoctorAppointmentServiceImpl doctorAppointmentService;
    @Autowired
    DiagnosisServiceImpl diagnosisService;
    @Autowired
    DoctorReferralServiceImpl doctorReferralService;
    @Test
    void addAndReadDoctorAppointment(){
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
        Doctor doctorTarget = new Doctor("Петр","Иванович","Альбертович",birthDate,"Невролог","Высшая категория","Доктор наук");
        Doctor createdDoctorTarget = doctorService.add(doctorTarget);
        Optional<Doctor> readDoctorTarget = doctorService.getById(createdDoctorTarget.getId());
        assertThat(doctorTarget)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorTarget.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
        Date date = Date.valueOf(LocalDate.now());

        DoctorReferral doctorReferral = new DoctorReferral(readDiagnosis.get(),readDoctor.get(),readPatient.get(),readDoctorTarget.get(),"676","Выписано",date,"Нервы кистей рук","Расписание");
        DoctorReferral createdDoctorReferral = doctorReferralService.add(doctorReferral);
        Optional<DoctorReferral> readDoctorReferral = doctorReferralService.getById(createdDoctorReferral.getId());
        assertThat(doctorReferral)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorReferral.get());


        DoctorAppointment doctorAppointment = new DoctorAppointment(date,"Выписано",readDoctorReferral.get());
        DoctorAppointment createdDoctorAppointment = doctorAppointmentService.add(doctorAppointment);
        Optional<DoctorAppointment> readDoctorAppointment = doctorAppointmentService.getById(createdDoctorAppointment.getId());
        assertThat(doctorAppointment)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorAppointment.get());

    }
    @Test
    void addAndDeleteDoctorAppointment(){

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
        Doctor doctorTarget = new Doctor("Петр","Иванович","Альбертович",birthDate,"Невролог","Высшая категория","Доктор наук");
        Doctor createdDoctorTarget = doctorService.add(doctorTarget);
        Optional<Doctor> readDoctorTarget = doctorService.getById(createdDoctorTarget.getId());
        assertThat(doctorTarget)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorTarget.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
        Date date = Date.valueOf(LocalDate.now());

        DoctorReferral doctorReferral = new DoctorReferral(readDiagnosis.get(),readDoctor.get(),readPatient.get(),readDoctorTarget.get(),"676","Выписано",date,"Нервы кистей рук","Расписание");
        DoctorReferral createdDoctorReferral = doctorReferralService.add(doctorReferral);
        Optional<DoctorReferral> readDoctorReferral = doctorReferralService.getById(createdDoctorReferral.getId());
        assertThat(doctorReferral)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorReferral.get());


        DoctorAppointment doctorAppointment = new DoctorAppointment(date,"Выписано",readDoctorReferral.get());
        DoctorAppointment createdDoctorAppointment = doctorAppointmentService.add(doctorAppointment);
        Optional<DoctorAppointment> readDoctorAppointment = doctorAppointmentService.getById(createdDoctorAppointment.getId());
        assertThat(doctorAppointment)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorAppointment.get());
        assertThat(doctorAppointment)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorAppointment.get());

        doctorAppointmentService.delete(createdDoctorAppointment.getId());
        readDoctorAppointment = doctorAppointmentService.getById(createdDoctorAppointment.getId());
        Assertions.assertFalse(readDoctorAppointment.isPresent());
    }
    @Test
    void editDoctorAppointment(){

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
        Doctor doctorTarget = new Doctor("Петр","Иванович","Альбертович",birthDate,"Невролог","Высшая категория","Доктор наук");
        Doctor createdDoctorTarget = doctorService.add(doctorTarget);
        Optional<Doctor> readDoctorTarget = doctorService.getById(createdDoctorTarget.getId());
        assertThat(doctorTarget)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorTarget.get());
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById("A00.0");
        Date date = Date.valueOf(LocalDate.now());

        DoctorReferral doctorReferral = new DoctorReferral(readDiagnosis.get(),readDoctor.get(),readPatient.get(),readDoctorTarget.get(),"676","Выписано",date,"Нервы кистей рук","Расписание");
        DoctorReferral createdDoctorReferral = doctorReferralService.add(doctorReferral);
        Optional<DoctorReferral> readDoctorReferral = doctorReferralService.getById(createdDoctorReferral.getId());
        assertThat(doctorReferral)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorReferral.get());


        DoctorAppointment doctorAppointment = new DoctorAppointment(date,"Выписано",readDoctorReferral.get());
        DoctorAppointment createdDoctorAppointment = doctorAppointmentService.add(doctorAppointment);
        Optional<DoctorAppointment> readDoctorAppointment = doctorAppointmentService.getById(createdDoctorAppointment.getId());
        assertThat(doctorAppointment)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorAppointment.get());

        doctorAppointment.setStatus("Пройден");
        DoctorAppointment editedDoctorAppointment = doctorAppointmentService.edit(doctorAppointment);
        Optional<DoctorAppointment> readEditedDoctorAppointment = doctorAppointmentService.getById(editedDoctorAppointment.getId());
        assertThat(doctorAppointment)
                .usingRecursiveComparison()
                .isEqualTo(readEditedDoctorAppointment.get());
    }
}
