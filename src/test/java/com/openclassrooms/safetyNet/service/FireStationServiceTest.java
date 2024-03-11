package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.entity.FireStation;
import com.openclassrooms.safetyNet.repository.FireStationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class FireStationServiceTest {

    @Mock
    FireStationRepository fireStationRepository;

    @InjectMocks
    FireStationServiceImpl fireStationService;

    private FireStation fireStation;

    @BeforeEach
    public void setup() {
        // do I need to put in all the parameters?
        fireStation = FireStation.builder()
                .id(1)
                .station("3")
                .address("abc")
                .build();
    }

    @Test
    public void FireStation_CreateFireStation_ReturnFireStationObject() {
        Mockito.lenient().when(fireStationRepository.save(fireStation)).thenReturn(fireStation);

        FireStation savedFireStation = fireStationService.createFireStation(fireStation);

        assertThat(savedFireStation).isNotNull();
    }

    @Test
    public void FireStation_UpdateFireStation_ReturnUpdatedFireStation() {
        Mockito.lenient().when(fireStationRepository.findById("1")).thenReturn(Optional.ofNullable(fireStation));
        Mockito.lenient().when(fireStationRepository.save(fireStation)).thenReturn(fireStation);
        fireStation.setStation("5");
        fireStation.setAddress("updated");

        FireStation updatedFireStation = fireStationService.updateFireStation(fireStation);
        assertThat(updatedFireStation.getStation()).isEqualTo("5");
        assertThat(updatedFireStation.getAddress()).isEqualTo("updated");
    }

    @Test
    public void FireStation_DeleteFireStation_ReturnNothing() {
        Mockito.lenient().when(fireStationRepository.findById("1")).thenReturn(Optional.ofNullable(fireStation));

        assertAll(() ->fireStationService.deleteFireStation(1));
    }
}