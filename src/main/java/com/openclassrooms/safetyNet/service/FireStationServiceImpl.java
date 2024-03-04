package com.openclassrooms.safetyNet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNet.entity.FireStation;
import com.openclassrooms.safetyNet.repository.FireStationRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class FireStationServiceImpl implements FireStationService {

    @Autowired
    FireStationRepository fireStationRepository;

    @Override
    public FireStation createFireStation(FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    @Override
    public FireStation updateFireStation(FireStation fireStation) {
        Optional<FireStation> fireStationOptional = fireStationRepository.findById(null);

        if (fireStationOptional.isEmpty()) {
            return null;
        }

        FireStation originalFireStation = fireStationOptional.get();

        if (!StringUtils.isBlank(fireStation.getStation())) {
            originalFireStation.setStation(fireStation.getStation());
        }

        return fireStationRepository.save(originalFireStation);

    }

    @Override
    public void deleteFireStation(Integer id) {
        FireStation fireStation = new FireStation();
        fireStation.setId(id);
        fireStationRepository.delete(fireStation);
    }

}
