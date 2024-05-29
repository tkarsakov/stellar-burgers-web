package com.intexsoft.stellarburgersweb.model;

import com.github.javafaker.Faker;
import com.intexsoft.stellarburgersweb.service.PropertiesService;

import static com.intexsoft.stellarburgersweb.service.PropertiesFile.TESTDATA;

public class User {
    private String email;
    private String name;
    private String password;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static User buildFakeUser() {
        Faker faker = new Faker();
        return new User(
                faker.internet().emailAddress(),
                faker.name().username(),
                faker.internet().password()
        );
    }

    public static User buildFromProperties() {
        return new User(
                PropertiesService.getProperty(TESTDATA, "user.email"),
                PropertiesService.getProperty(TESTDATA, "user.name"),
                PropertiesService.getProperty(TESTDATA, "user.password")
        );
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
