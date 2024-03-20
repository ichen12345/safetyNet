package com.openclassrooms.safetyNet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.service.MedicalRecordJsonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MedicalRecordController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordJsonService medicalRecordJsonService;

    @Autowired
    private ObjectMapper objectMapper;

    private MedicalRecord medicalRecord;


    @BeforeEach
    public void setUp() {
        String[] allergies = {"allergies"};
        String[] med = {"med"};
        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Ana");
        medicalRecord.setLastName("Smith");
        medicalRecord.setBirthdate("birth");
        medicalRecord.setMedications(Arrays.asList(med));
        medicalRecord.setAllergies(Arrays.asList(allergies));

    }

    @Test
    void addMedicalRecord() throws Exception{
        when(medicalRecordJsonService.createMedicalRecord(medicalRecord)).thenReturn(medicalRecord);

        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isOk());
    }

    @Test
    void updateMedicalRecord() throws Exception{
        when(medicalRecordJsonService.updateMedicalRecord(medicalRecord)).thenReturn(medicalRecord);

        mockMvc.perform(put("/medicalRecord", medicalRecord)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMedicalRecord() throws Exception{
        doNothing().when(medicalRecordJsonService).deleteMedicalRecord(medicalRecord);
        mockMvc.perform(delete("/medicalRecord", medicalRecord)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isOk());
    }
}