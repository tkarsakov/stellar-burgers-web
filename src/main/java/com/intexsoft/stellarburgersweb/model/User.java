package com.intexsoft.stellarburgersweb.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.github.javafaker.Faker;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonGetter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter
    public String getPassword() {
        return password;
    }

    @JsonGetter
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
