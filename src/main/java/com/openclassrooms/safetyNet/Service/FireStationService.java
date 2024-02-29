package com.openclassrooms.safetyNet.Service;

import com.openclassrooms.safetyNet.Model.FireStation;

public interface FireStationService {
    FireStation createFireStation(FireStation fireStation);

    FireStation updateFireStation(FireStation fireStation);

    void deleteFireStation(Integer id);
}
