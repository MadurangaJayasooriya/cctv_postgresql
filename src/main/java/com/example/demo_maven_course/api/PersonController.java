package com.example.demo_maven_course.api;

import com.example.demo_maven_course.model.Person;
import com.example.demo_maven_course.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
// interface of the API calls service layer for functions and deal with the HTTP protocols
@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person){
        personService.addPerson(person);

    }
    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }


    @GetMapping(path = "{id}")
    public Person getPersonByID(@PathVariable UUID id){
        return personService.getPersonById(id).orElse( null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id ){
        personService.deletePerson(id);
    }


    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @RequestBody Person personToUpdate ){
        personService.updatePerson(id,personToUpdate);
    }

}
