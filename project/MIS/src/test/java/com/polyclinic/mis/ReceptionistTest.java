package com.polyclinic.mis;

import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.models.Receptionist;
import com.polyclinic.mis.service.impl.PatientServiceImpl;
import com.polyclinic.mis.service.impl.ReceptionistServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class ReceptionistTest {
    @Autowired
    ReceptionistServiceImpl receptionistService;
    @Test
    void addAndReadReceptionist(){
        Date birthDate = Date.valueOf("2000-02-01");
        Receptionist receptionist = new Receptionist("Генадий","Чернышев","Олегович",birthDate);
        Receptionist createdReceptionist = receptionistService.add(receptionist);
        Optional<Receptionist> readReceptionist = receptionistService.getById(createdReceptionist.getId());
        assertThat(receptionist)
                .usingRecursiveComparison()
                .isEqualTo(readReceptionist.get());
    }
    @Test
    void addAndDeleteReceptionist(){
        Date birthDate = Date.valueOf("2000-02-01");
        Receptionist receptionist = new Receptionist("Генадий","Чернышев","Олегович",birthDate);
        Receptionist createdReceptionist = receptionistService.add(receptionist);
        Optional<Receptionist> readReceptionist = receptionistService.getById(createdReceptionist.getId());
        assertThat(receptionist)
                .usingRecursiveComparison()
                .isEqualTo(readReceptionist.get());
        receptionistService.delete(createdReceptionist.getId());
        readReceptionist = receptionistService.getById(createdReceptionist.getId());
        Assertions.assertFalse(readReceptionist.isPresent());
    }
    @Test
    void editReceptionist(){
        Date birthDate = Date.valueOf("2000-02-01");
        Receptionist receptionist = new Receptionist("Генадий","Чернышев","Олегович",birthDate);
        Receptionist createdReceptionist = receptionistService.add(receptionist);
        Optional<Receptionist> readReceptionist = receptionistService.getById(createdReceptionist.getId());
        assertThat(receptionist)
                .usingRecursiveComparison()
                .isEqualTo(readReceptionist.get());
        receptionist.setFirstName("Петр");
        Receptionist editedPatient = receptionistService.edit(receptionist);
        Optional<Receptionist> readEditedPatient = receptionistService.getById(editedPatient.getId());
        assertThat(receptionist)
                .usingRecursiveComparison()
                .isEqualTo(readEditedPatient.get());
    }

}
