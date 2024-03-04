package com.openclassrooms.safetyNet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.safetyNet.entity.FireStation;

public interface FireStationRepository extends JpaRepository<FireStation, String>{
    List<FireStation> findByStation(String station);
    String findByAddress(String address);
}
