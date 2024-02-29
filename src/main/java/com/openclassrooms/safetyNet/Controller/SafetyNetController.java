package com.openclassrooms.safetyNet.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.openclassrooms.safetyNet.Model.FireStation;
import com.openclassrooms.safetyNet.Model.MedicalRecord;
import com.openclassrooms.safetyNet.Model.Person;
import com.openclassrooms.safetyNet.Service.FireStationService;
import com.openclassrooms.safetyNet.Service.PersonService;
import com.openclassrooms.safetyNet.Service.SafetyNetService;

@RestController
public class SafetyNetController {

    @Autowired
    SafetyNetService safetyNetService;

    // @GetMapping("/firestation?stationNumber={station_number}")
    // public ModelAndView getListOfPeopleByFireStation() {
    //     String viewName = "peopleByFireStation";

    //     Map<String, Object> model = new HashMap<>();

    //     model.put("numberOfPeople", "1234");

    //     return new ModelAndView(viewName, model);
    // }

    //http://localhost:8080/firestation?stationNumber=1&name=Ivyt
    @GetMapping("/firestation")
    public List<Person> getPeopleFromFireStation(@RequestParam("stationNumber") String station) {
        return safetyNetService.personServicedFirestation(station);
    }
    
    // @GetMapping("/childAlert")
    // public List<Children> getChildAlert(@RequestParam String address, @RequestBody Person person, @RequestBody MedicalRecord medicalRecord) {
    //     return safetyNetService.childrenFromAddress(person, medicalRecord);
    // }
}
