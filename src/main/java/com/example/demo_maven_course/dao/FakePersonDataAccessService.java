package com.example.demo_maven_course.dao;

import com.example.demo_maven_course.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
//Do all the ground level database operations ... communicate with the database
@Repository("fakeDao")//Name of the repository referred by the service layer
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();



    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if (personMaybe.isEmpty()){

            return 0;
        }else {
            DB.remove(personMaybe.get());
            return 1;
        }

    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return selectPersonById(id)
                .map(person ->{

                    int indexOfPersonUpdate = DB.indexOf(person);
                    if(indexOfPersonUpdate>=0){
                        DB.set(indexOfPersonUpdate,new Person(id, update.getName()));
                        return 1;
                    }else{return 0;}
                } )
                .orElse(0);
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }


}
