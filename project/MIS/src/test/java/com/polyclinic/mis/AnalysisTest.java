package com.polyclinic.mis;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.service.impl.AnalysisReferralServiceImpl;
import com.polyclinic.mis.service.impl.AnalysisServiceImpl;
import com.polyclinic.mis.service.impl.AssistantServiceImpl;
import com.polyclinic.mis.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AnalysisTest {
    @Autowired
    AnalysisServiceImpl analysisService;
    @Autowired
    AssistantServiceImpl assistantService;
    @Autowired
    PatientServiceImpl patientService;
    @Test
    void addAndReadAnalysis(){
        Date birthDate = Date.valueOf("2000-03-01");
        Assistant assistant = new Assistant("Виктор","Михайлов","Олегович",birthDate);
        Assistant createdAssistant = assistantService.add(assistant);
        Optional<Assistant> readAssistant = assistantService.getById(createdAssistant.getId());
        assertThat(assistant)
                .usingRecursiveComparison()
                .isEqualTo(readAssistant.get());
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,"124563","Компания1","123976","Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());



        String dateStr = "2023-02-01 18:38";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateStr, formatter);


//        LocalDateTime date = LocalDateTime.valueOf("2023-02-01 18:38:03");
        Analysis analysis = new Analysis(readPatient.get(),"Общий анализ крови","Заключение",readAssistant.get(),date);
        Analysis createdAnalysis = analysisService.add(analysis);
        Optional<Analysis> readAnalysis= analysisService.getById(createdAnalysis.getId());
        assertThat(analysis)
                .usingRecursiveComparison()
                .isEqualTo(readAnalysis.get());
    }
    @Test
    void addAndDeleteAnalysis(){
        Date birthDate = Date.valueOf("2000-02-01");
        Assistant assistant = new Assistant("Виктор","Михайлов","Олегович",birthDate);
        Assistant createdAssistant = assistantService.add(assistant);
        Optional<Assistant> readAssistant = assistantService.getById(createdAssistant.getId());
        assertThat(assistant)
                .usingRecursiveComparison()
                .isEqualTo(readAssistant.get());
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,"124563","Компания1","123976","Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        String dateStr = "2023-02-01 18:38";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
        Analysis analysis = new Analysis(readPatient.get(),"Общий анализ крови","Заключение",readAssistant.get(),date);
        Analysis createdAnalysis = analysisService.add(analysis);
        Optional<Analysis> readAnalysis= analysisService.getById(createdAnalysis.getId());
        assertThat(analysis)
                .usingRecursiveComparison()
                .isEqualTo(readAnalysis.get());

        analysisService.delete(createdAnalysis.getId());
        readAnalysis = analysisService.getById(createdAnalysis.getId());
        Assertions.assertFalse(readAnalysis.isPresent());
    }
    @Test
    void editAnalysis(){
        Date birthDate = Date.valueOf("2001-02-01");
        Assistant assistant = new Assistant("Виктор","Михайлов","Олегович",birthDate);
        Assistant createdAssistant = assistantService.add(assistant);
        Optional<Assistant> readAssistant = assistantService.getById(createdAssistant.getId());
        assertThat(assistant)
                .usingRecursiveComparison()
                .isEqualTo(readAssistant.get());
        Patient patient = new Patient("Иван","Петров","Михайлович",birthDate,"124563","Компания1","123976","Ул.Комарова д.23 кв.2","АЛЬТЕНАР","Системный администратор");
        Patient createdPatient = patientService.add(patient);
        Optional<Patient> readPatient = patientService.getById(createdPatient.getId());
        assertThat(patient)
                .usingRecursiveComparison()
                .isEqualTo(readPatient.get());
        String dateStr = "2023-02-01 18:38";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
        Analysis analysis = new Analysis(readPatient.get(),"Общий анализ крови","Заключение",readAssistant.get(),date);
        Analysis createdAnalysis = analysisService.add(analysis);
        Optional<Analysis> readAnalysis= analysisService.getById(createdAnalysis.getId());
        assertThat(analysis)
                .usingRecursiveComparison()
                .isEqualTo(readAnalysis.get());

        analysis.setType("Анализ крови на тестостерон");
        Analysis editedAnalysis = analysisService.edit(analysis);
        Optional<Analysis> readEditedAnalysis = analysisService.getById(editedAnalysis.getId());
        assertThat(analysis)
                .usingRecursiveComparison()
                .isEqualTo(readEditedAnalysis.get());
    }
}
