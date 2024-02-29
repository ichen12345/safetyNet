package com.openclassrooms.safetyNet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetyNet.Model.FireStation;
import com.openclassrooms.safetyNet.Service.FireStationService;

@RestController
@RequestMapping("firestation")
public class FireStationController {
    
    @Autowired
    FireStationService fireStationService;

    @PostMapping()
    public FireStation addFireStation(@RequestBody FireStation fireStation) {
        return fireStationService.createFireStation(fireStation);
    }
    
    @PutMapping("/{id}")
    public FireStation updateFireStation(@RequestBody FireStation fireStation, @PathVariable Integer id) {
        return fireStationService.updateFireStation(fireStation);
    }

    @DeleteMapping("/{id}")
    public void deleteFireStation(@PathVariable Integer id) {
        fireStationService.deleteFireStation(id);
    }
}
