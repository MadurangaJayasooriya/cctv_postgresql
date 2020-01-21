package com.example.demo_maven_course.dao;

import com.example.demo_maven_course.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.awt.event.PaintEvent;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("postgres")//name of the repository as referenced by the service
public class PersonDataAccessService implements PersonDao {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name FROM person";
        List<Person> people=jdbcTemplate.query(sql, (resultSet,i) ->{

            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id,name);
        } );
        return people;
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {



        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {


        final String sql = "SELECT id, name FROM person WHERE id = ?";
        Person person=jdbcTemplate.queryForObject(sql,new Object[]{id},  (resultSet,i) ->{

            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(personId,name);
        } );

        return Optional.ofNullable(person);
    }
}