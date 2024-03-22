package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.FireStation;

import java.util.List;

public interface FireStationJsonService {
    FireStation createFireStation(FireStation fireStation);

    FireStation updateFireStation(FireStation fireStation);

    void deleteFireStation(FireStation fireStation);

    List<FireStation> getFireStations();
}
