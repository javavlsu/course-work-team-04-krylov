package com.polyclinic.mis;

import com.polyclinic.mis.models.*;
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
public class DoctorReferralAppointmentTest {
    @Autowired
    DoctorServiceImpl doctorService;
    @Autowired
    PatientServiceImpl patientService;
    @Autowired
    DoctorReferralAppointmentServiceImpl doctorAppointmentService;
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
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,"124563","Компания1","123976","Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
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

        String dateStr = "2023-02-03 18:38";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateStr, formatter);


        DoctorReferral doctorReferral = new DoctorReferral(readDiagnosis.get(),readDoctor.get(),readPatient.get(),readDoctorTarget.get(),"676","Выписано",date,"Нервы кистей рук","Расписание");
        DoctorReferral createdDoctorReferral = doctorReferralService.add(doctorReferral);
        Optional<DoctorReferral> readDoctorReferral = doctorReferralService.getById(createdDoctorReferral.getId());
        assertThat(doctorReferral)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorReferral.get());


        DoctorReferralAppointment doctorReferralAppointment = new DoctorReferralAppointment(date,"Использована",readDoctorReferral.get());
        DoctorReferralAppointment createdDoctorReferralAppointment = doctorAppointmentService.add(doctorReferralAppointment);
        Optional<DoctorReferralAppointment> readDoctorAppointment = doctorAppointmentService.getById(createdDoctorReferralAppointment.getId());
        assertThat(doctorReferralAppointment)
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
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,"124563","Компания1","123976","Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
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

        String dateStr = "2023-05-03 18:38";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateStr, formatter);

        DoctorReferral doctorReferral = new DoctorReferral(readDiagnosis.get(),readDoctor.get(),readPatient.get(),readDoctorTarget.get(),"676","Выписано",date,"Нервы кистей рук","Расписание");
        DoctorReferral createdDoctorReferral = doctorReferralService.add(doctorReferral);
        Optional<DoctorReferral> readDoctorReferral = doctorReferralService.getById(createdDoctorReferral.getId());
        assertThat(doctorReferral)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorReferral.get());


        DoctorReferralAppointment doctorReferralAppointment = new DoctorReferralAppointment(date,"Ожидает подтверждения",readDoctorReferral.get());
        DoctorReferralAppointment createdDoctorReferralAppointment = doctorAppointmentService.add(doctorReferralAppointment);
        Optional<DoctorReferralAppointment> readDoctorAppointment = doctorAppointmentService.getById(createdDoctorReferralAppointment.getId());
        assertThat(doctorReferralAppointment)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorAppointment.get());
        assertThat(doctorReferralAppointment)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorAppointment.get());

        doctorAppointmentService.delete(createdDoctorReferralAppointment.getId());
        readDoctorAppointment = doctorAppointmentService.getById(createdDoctorReferralAppointment.getId());
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
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,"124563","Компания1","123976","Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
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
        String dateStr = "2023-05-03 18:38";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateStr, formatter);

        DoctorReferral doctorReferral = new DoctorReferral(readDiagnosis.get(),readDoctor.get(),readPatient.get(),readDoctorTarget.get(),"676","Выписано",date,"Нервы кистей рук","Расписание");
        DoctorReferral createdDoctorReferral = doctorReferralService.add(doctorReferral);
        Optional<DoctorReferral> readDoctorReferral = doctorReferralService.getById(createdDoctorReferral.getId());
        assertThat(doctorReferral)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorReferral.get());


        DoctorReferralAppointment doctorReferralAppointment = new DoctorReferralAppointment(date,"Выписано",readDoctorReferral.get());
        DoctorReferralAppointment createdDoctorReferralAppointment = doctorAppointmentService.add(doctorReferralAppointment);
        Optional<DoctorReferralAppointment> readDoctorAppointment = doctorAppointmentService.getById(createdDoctorReferralAppointment.getId());
        assertThat(doctorReferralAppointment)
                .usingRecursiveComparison()
                .isEqualTo(readDoctorAppointment.get());

        doctorReferralAppointment.setStatus("Ожидает подтверждения");
        DoctorReferralAppointment editedDoctorReferralAppointment = doctorAppointmentService.edit(doctorReferralAppointment);
        Optional<DoctorReferralAppointment> readEditedDoctorAppointment = doctorAppointmentService.getById(editedDoctorReferralAppointment.getId());
        assertThat(doctorReferralAppointment)
                .usingRecursiveComparison()
                .isEqualTo(readEditedDoctorAppointment.get());
    }
}
