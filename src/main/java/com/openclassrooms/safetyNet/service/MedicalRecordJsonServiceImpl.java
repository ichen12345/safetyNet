package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class MedicalRecordJsonServiceImpl implements MedicalRecordJsonService{

    @Autowired
    Data data;

    @Override
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        List<MedicalRecord> medicalRecords = data.getMedicalrecords();
        medicalRecords.add(medicalRecord);
        return medicalRecord;
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        for(MedicalRecord medicalRecordData : data.getMedicalrecords()) {
            if(medicalRecordData.getFirstName().equals(medicalRecord.getFirstName()) && medicalRecordData.getLastName().equals(medicalRecord.getLastName())) {
                medicalRecordData.setMedications(medicalRecord.getMedications());
                medicalRecordData.setBirthdate(medicalRecord.getBirthdate());
                medicalRecordData.setAllergies(medicalRecord.getAllergies());
                return medicalRecordData;
            }
        }
        return null;
    }

    @Override
    public void deleteMedicalRecord(MedicalRecord medicalRecord) {
        List<MedicalRecord> medicalRecords = data.getMedicalrecords();

        Iterator<MedicalRecord> it = medicalRecords.iterator();
        while (it.hasNext()) {
            MedicalRecord medicalRecordData = it.next();
            if (medicalRecordData.getFirstName().equals(medicalRecord.getFirstName()) && medicalRecordData.getLastName().equals(medicalRecord.getLastName())) {
                it.remove();
            }
        }
    }

    @Override
    public List<MedicalRecord> getMedicalRecords() {
        return data.getMedicalrecords();
    }
}
