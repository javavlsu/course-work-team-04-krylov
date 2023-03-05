package com.polyclinic.mis;

import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.service.impl.AssistantServiceImpl;
import com.polyclinic.mis.service.impl.DoctorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class AssistantTest {
    @Autowired
    AssistantServiceImpl assistantService;
    @Test
    void addAndReadAssistant(){
        Date birthDate = Date.valueOf("2000-02-01");
        Assistant assistant = new Assistant("Виктор","Михайлов","Олегович",birthDate);
        Assistant createdAssistant = assistantService.add(assistant);
        Optional<Assistant> readAssistant = assistantService.getById(createdAssistant.getId());
        assertThat(assistant)
                .usingRecursiveComparison()
                .isEqualTo(readAssistant.get());
    }
    @Test
    void addAndDeleteAssistant(){
        Date birthDate = Date.valueOf("2000-02-01");
        Assistant assistant = new Assistant("Виктор","Михайлов","Олегович",birthDate);
        Assistant createdAssistant = assistantService.add(assistant);
        Optional<Assistant> readAssistant = assistantService.getById(createdAssistant.getId());
        assertThat(assistant)
                .usingRecursiveComparison()
                .isEqualTo(readAssistant.get());
        assistantService.delete(createdAssistant.getId());
        readAssistant = assistantService.getById(createdAssistant.getId());
        Assertions.assertFalse(readAssistant.isPresent());
    }
    @Test
    void editAssistant(){
        Date birthDate = Date.valueOf("2000-02-01");
        Assistant assistant = new Assistant("Виктор","Михайлов","Олегович",birthDate);
        Assistant createdAssistant = assistantService.add(assistant);
        Optional<Assistant> readAssistant = assistantService.getById(createdAssistant.getId());
        assertThat(assistant)
                .usingRecursiveComparison()
                .isEqualTo(readAssistant.get());
        assistant.setFirstName("Петр");
        Assistant editedAssistant = assistantService.edit(assistant);
        Optional<Assistant> readEditedPatient = assistantService.getById(editedAssistant.getId());
        assertThat(assistant)
                .usingRecursiveComparison()
                .isEqualTo(readEditedPatient.get());
    }
}
