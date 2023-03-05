package com.polyclinic.mis;

import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.Diagnosis;
import com.polyclinic.mis.service.impl.AssistantServiceImpl;
import com.polyclinic.mis.service.impl.DiagnosisServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DiagnosisTest {
    @Autowired
    DiagnosisServiceImpl diagnosisService;
    @Test
    void addAndReadAndDeleteDiagnosis(){
        Diagnosis diagnosis = new Diagnosis("C00.0","Злокачественное новообразование наружной поверхности губы");
        Diagnosis createdDiagnosis = diagnosisService.add(diagnosis);
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById(createdDiagnosis.getId());
        assertThat(diagnosis)
                .usingRecursiveComparison()
                .isEqualTo(readDiagnosis.get());
        diagnosisService.delete(createdDiagnosis.getId());
        readDiagnosis = diagnosisService.getById(createdDiagnosis.getId());
        Assertions.assertFalse(readDiagnosis.isPresent());
    }
    @Test
    void editAndDeleteDiagnosis(){
        Diagnosis diagnosis = new Diagnosis("C00.0","Злокачественное новообразование наружной поверхности губы");
        Diagnosis createdDiagnosis = diagnosisService.add(diagnosis);
        Optional<Diagnosis> readDiagnosis = diagnosisService.getById(createdDiagnosis.getId());
        assertThat(diagnosis)
                .usingRecursiveComparison()
                .isEqualTo(readDiagnosis.get());
        diagnosis.setId("C00.1");
        diagnosis.setDescription("Злокачественное новообразование наружной поверхности нижней губы");
        Diagnosis editedDiagnosis = diagnosisService.edit(diagnosis);
        Optional<Diagnosis> readEditedDiagnosis = diagnosisService.getById(editedDiagnosis.getId());
        assertThat(diagnosis)
                .usingRecursiveComparison()
                .isEqualTo(readEditedDiagnosis.get());
        diagnosisService.delete(createdDiagnosis.getId());
        readDiagnosis = diagnosisService.getById(createdDiagnosis.getId());
        Assertions.assertFalse(readDiagnosis.isPresent());
    }
}
