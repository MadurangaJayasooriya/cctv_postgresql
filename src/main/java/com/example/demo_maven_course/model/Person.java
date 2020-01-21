package com.example.demo_maven_course.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Person {
    private final UUID id;
    @NotBlank// Don't allow blank inputs as the name parameter
    private final String name;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Person(@JsonProperty("id")UUID id,@JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }



}
