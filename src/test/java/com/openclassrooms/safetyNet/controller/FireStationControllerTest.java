package com.openclassrooms.safetyNet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.entity.FireStation;
import com.openclassrooms.safetyNet.service.FireStationService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FireStationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    @Autowired
    private ObjectMapper objectMapper;

    private FireStation fireStation;

    @BeforeEach
    public void setup() {
        fireStation = FireStation.builder()
                .id(1)
                .station("3")
                .address("abc")
                .build();
    }

    @Test
    void addFireStation() throws Exception{
        when(fireStationService.createFireStation(fireStation)).thenReturn(fireStation);

        mockMvc.perform(post("/fireStation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fireStation)))
                .andExpect(status().isOk());
    }

    @Test
    void updateFireStation() throws Exception{
        when(fireStationService.updateFireStation(fireStation)).thenReturn(fireStation);

        mockMvc.perform(put("/fireStation/{id}", fireStation.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fireStation)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteFireStation() throws Exception{
        doNothing().when(fireStationService).deleteFireStation(fireStation.getId());
        mockMvc.perform(delete("/fireStation/{id}", fireStation.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fireStation.getId())))
                .andExpect(status().isOk());
    }
}