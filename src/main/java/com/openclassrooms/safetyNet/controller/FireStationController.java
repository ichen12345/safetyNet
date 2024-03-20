package com.openclassrooms.safetyNet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.service.FireStationJsonService;

@RestController
@RequestMapping("fireStation")
public class FireStationController {

    private Logger logger = LoggerFactory.getLogger(FireStationController.class);
    @Autowired
    FireStationJsonService fireStationService;

    @PostMapping()
    public FireStation addFireStation(@RequestBody FireStation fireStation) {
        logger.info("Post request for /fireStation endpoint to add a fire station {}", fireStation);
        return fireStationService.createFireStation(fireStation);
    }
    
    @PutMapping()
    public FireStation updateFireStation(@RequestBody FireStation fireStation) {
        logger.info("Put request for /fireStation endpoint to update the fire station {}", fireStation);
        return fireStationService.updateFireStation(fireStation);
    }

    @DeleteMapping()
    public void deleteFireStation(@RequestBody FireStation fireStation) {
        logger.info("Delete request for /fireStation endpoint to delete the fire station {}", fireStation);
        fireStationService.deleteFireStation(fireStation);
    }
}
