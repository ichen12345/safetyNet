package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class FireStationJsonServiceTest {

    @Mock
    Data data;
    @InjectMocks
    FireStationJsonServiceImpl fireStationService;

    private FireStation fireStation;

    private List<FireStation> fireStationList;

    @BeforeEach
    public void setup() {
        fireStation = new FireStation();
        fireStation.setAddress("abc");
        fireStation.setStation("3");

        fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);
    }

    @Test
    public void FireStation_CreateFireStation_ReturnFireStationObject() {
        Mockito.lenient().when(data.getFirestations()).thenReturn(fireStationList);

        FireStation savedFireStation = fireStationService.createFireStation(fireStation);

        assertThat(savedFireStation).isNotNull();
    }

    @Test
    public void FireStation_UpdateFireStation_ReturnUpdatedFireStation() {
        Mockito.lenient().when(data.getFirestations()).thenReturn(fireStationList);
        fireStation.setStation("5");

        FireStation updatedFireStation = fireStationService.updateFireStation(fireStation);
        assertThat(updatedFireStation.getStation()).isEqualTo("5");
    }

    @Test
    public void FireStation_DeleteFireStation_ReturnNothing() {

    }
}