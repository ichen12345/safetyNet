package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Data;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class MedicalRecordJsonServiceTest {

    @Mock
    Data data;

    @InjectMocks
    private MedicalRecordJsonServiceImpl medicalRecordService;

    private MedicalRecord medicalRecord;

    private List<MedicalRecord> medicalRecords;

    @BeforeEach
    public void setUp() {
        String[] allergies = {"allergies"};
        String[] med = {"med"};
        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Ana");
        medicalRecord.setLastName("Smith");
        medicalRecord.setMedications(Arrays.asList(med));
        medicalRecord.setAllergies(Arrays.asList(allergies));

        medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord);
    }

    @Test
    public void medicalRecord_createMedicalRecord_thenReturnMedicalRecord() {
        Mockito.lenient().when(data.getMedicalrecords()).thenReturn(medicalRecords);

        MedicalRecord savedMedicalRecord = medicalRecordService.createMedicalRecord(medicalRecord);

        assertThat(savedMedicalRecord).isNotNull();
    }

    @Test
    public void medicalRecord_updateMedicalRecord_thenReturnUpdatedMedicalRecord() {
        Mockito.lenient().when(data.getMedicalrecords()).thenReturn(medicalRecords);
        String[] newAllergies = {"newAllergies"};
        medicalRecord.setAllergies(Arrays.asList(newAllergies));

        MedicalRecord updatedMed = medicalRecordService.updateMedicalRecord(medicalRecord);
        assertThat(updatedMed).isNotNull();
    }

    @Test
    public void medicalRecord_deleteMedicalRecord_thenNothing() {
        Mockito.lenient().when(data.getMedicalrecords()).thenReturn(medicalRecords);

        medicalRecordService.deleteMedicalRecord(medicalRecord);

        assert !medicalRecords.contains(medicalRecord);
    }
}