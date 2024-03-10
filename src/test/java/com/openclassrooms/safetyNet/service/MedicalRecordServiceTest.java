package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.entity.MedicalRecord;
import com.openclassrooms.safetyNet.entity.Person;
import com.openclassrooms.safetyNet.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private MedicalRecordServiceImpl medicalRecordService;

    private MedicalRecord medicalRecord;

    @BeforeEach
    public void setUp() {
        String[] allergies = {"allergies"};
        String[] med = {"med"};
        medicalRecord = MedicalRecord.builder()
                .firstName("Ana")
                .lastName("Smith")
                .allergies(Arrays.asList(allergies))
                .medications(Arrays.asList(med))
                .build();
    }

    @Test
    public void medicalRecord_createMedicalRecord_thenReturnMedicalRecord() {
        Mockito.lenient().when(medicalRecordRepository.save(medicalRecord)).thenReturn(medicalRecord);

        MedicalRecord savedMedicalRecord = medicalRecordService.createMedicalRecord(medicalRecord);

        assertThat(savedMedicalRecord).isNotNull();
    }

    @Test
    public void medicalRecord_updateMedicalRecord_thenReturnUpdatedMedicalRecord() {
        Mockito.lenient().when(medicalRecordRepository.findById(1)).thenReturn(Optional.ofNullable(medicalRecord));
        Mockito.lenient().when(medicalRecordRepository.save(Mockito.any(MedicalRecord.class))).thenReturn(medicalRecord);

        // why is updateMed null?
        MedicalRecord updatedMed = medicalRecordService.updateMedicalRecord(medicalRecord);
        assertThat(updatedMed).isNotNull();
    }

    @Test
    public void medicalRecord_deleteMedicalRecord_thenNothing() {
        Mockito.lenient().when(medicalRecordRepository.findById(1)).thenReturn(Optional.ofNullable(medicalRecord));

        assertAll(() ->medicalRecordService.deleteMedicalRecord(1));
    }
}