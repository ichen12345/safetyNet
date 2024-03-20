package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.FireStation;

public interface FireStationJsonService {
    FireStation createFireStation(FireStation fireStation);

    FireStation updateFireStation(FireStation fireStation);

    void deleteFireStation(FireStation fireStation);
}
