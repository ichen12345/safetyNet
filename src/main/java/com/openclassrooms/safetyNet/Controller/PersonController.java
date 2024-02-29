package com.openclassrooms.safetyNet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.safetyNet.Model.Person;
import com.openclassrooms.safetyNet.Service.PersonService;

@RestController
@RequestMapping("person")
public class PersonController {

    // @GetMapping("/personForm")
    // public ModelAndView showPersonForm() {
    //     String viewName = "peopleByFireStation";

    //     Map<String, Object> model = new HashMap<>();

    //     model.put("person", new Person());

    //     return new ModelAndView(viewName, model);
    // }

    @Autowired
    PersonService personService;

    @PostMapping()
    public Person addPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable String id) {
        return personService.updatePerson(person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Integer id) {
        personService.deletePerson(id);
    }
    
}
