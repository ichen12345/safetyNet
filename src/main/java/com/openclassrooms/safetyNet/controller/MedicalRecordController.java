package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.service.MedicalRecordJsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicalRecord")
public class MedicalRecordController {

    private Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    @Autowired
    MedicalRecordJsonService medicalRecordJsonService;

    @PostMapping()
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.info("Post request for /medicalRecord endpoint to add a {}", medicalRecord);
        return medicalRecordJsonService.createMedicalRecord(medicalRecord);
    }

    @PutMapping("")
    public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.info("Put request for /medicalRecord endpoint to update a medical record to {}", medicalRecord);
        return medicalRecordJsonService.updateMedicalRecord(medicalRecord);
    }

    @DeleteMapping("")
    public void deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.info("Delete request for /medicalRecord endpoint to delete a {}", medicalRecord);
        medicalRecordJsonService.deleteMedicalRecord(medicalRecord);
    }

    @GetMapping("/all")
    public List<MedicalRecord> medicalRecords() {
        return medicalRecordJsonService.getMedicalRecords();
    }
}
