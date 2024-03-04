package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.entity.FireStation;

public interface FireStationService {
    FireStation createFireStation(FireStation fireStation);

    FireStation updateFireStation(FireStation fireStation);

    void deleteFireStation(Integer id);
}
