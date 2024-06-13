package com.example.demo1.model;

public class Customer {
    public String _firstName;
    public String _lastName;
    public Customer(String firstName, String lastName){
        _firstName = firstName;
        _lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer [First Name: " + _firstName + ", Last Name: " + _lastName + "]";
    }
}
