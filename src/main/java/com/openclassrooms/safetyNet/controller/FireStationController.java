package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.service.FireStationJsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fireStation")
public class FireStationController {

    private Logger logger = LoggerFactory.getLogger(FireStationController.class);
    @Autowired
    FireStationJsonService fireStationService;

    @PostMapping()
    public FireStation addFireStation(@RequestBody FireStation fireStation) {
        logger.info("Post request for /fireStation endpoint to add a {}", fireStation);
        return fireStationService.createFireStation(fireStation);
    }
    
    @PutMapping()
    public FireStation updateFireStation(@RequestBody FireStation fireStation) {
        logger.info("Put request for /fireStation endpoint to update the fire station/address mapping to {}", fireStation);
        return fireStationService.updateFireStation(fireStation);
    }

    @DeleteMapping()
    public void deleteFireStation(@RequestBody FireStation fireStation) {
        logger.info("Delete request for /fireStation endpoint to delete the {}", fireStation);
        fireStationService.deleteFireStation(fireStation);
    }

    @GetMapping("/all")
    public List<FireStation> fireStations() {
        return fireStationService.getFireStations();
    }
}
