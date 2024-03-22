package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Data;
import com.openclassrooms.safetyNet.model.FireStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class FireStationJsonServiceImpl implements FireStationJsonService{

    @Autowired
    Data data;

    @Override
    public FireStation createFireStation(FireStation fireStation) {
        List<FireStation> fireStationList = data.getFirestations();
        fireStationList.add(fireStation);
        return fireStation;
    }

    @Override
    public List<FireStation> getFireStations() {
        return data.getFirestations();
    }

    @Override
    public FireStation updateFireStation(FireStation fireStation) {
        for(FireStation fireStationData : data.getFirestations()) {
            if(fireStationData.getAddress().equals(fireStation.getAddress())) {
                fireStationData.setStation(fireStation.getStation());
                return fireStationData;
            }
        }
        return null;
    }

    @Override
    public void deleteFireStation(FireStation fireStation) {
        List<FireStation> fireStations = data.getFirestations();

        Iterator<FireStation> it = fireStations.iterator();
        while (it.hasNext()) {
            FireStation fireStationData = it.next();
            if (fireStationData.getAddress().equals(fireStation.getAddress())) {
                it.remove();
            }
        }
    }
}
