package com.openclassrooms.safetyNet.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.safetyNet.Model.FireStation;

public interface FireStationRepository extends JpaRepository<FireStation, String>{
    List<FireStation> findByStation(String station);
}
