package com.openclassrooms.safetyNet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNet.entity.MedicalRecord;
import com.openclassrooms.safetyNet.repository.MedicalRecordRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService{

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        Optional<MedicalRecord> medicalRecordOptional = medicalRecordRepository.findById(medicalRecord.getId());

        if(medicalRecordOptional.isEmpty()) {
            return null;
        }

        MedicalRecord originalMedicalRecord = medicalRecordOptional.get();

        // if Date is null
        if(medicalRecord.getBirthDate() != null){
            originalMedicalRecord.setBirthDate(medicalRecord.getBirthDate());
        }
        
        // if the list is empty
        if(!medicalRecord.getMedications().isEmpty()){
            originalMedicalRecord.setMedications(medicalRecord.getMedications());
        }
        if(!medicalRecord.getAllergies().isEmpty()){
            originalMedicalRecord.setAllergies(medicalRecord.getAllergies());
        }

        return medicalRecordRepository.save(originalMedicalRecord);

    }

    @Override
    public void deleteMedicalRecord(Integer id) {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setId(id);
        medicalRecordRepository.delete(medicalRecord);
    }
}
