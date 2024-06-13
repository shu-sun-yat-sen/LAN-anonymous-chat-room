package com.example.demo1.model;

public class Person {
    public String _firstName;
    public String _lastName;
    public Person(String firstName, String lastName){
        _firstName = firstName;
        _lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person [First Name: " + _firstName + ", Last Name: " + _lastName + "]";
    }
}
