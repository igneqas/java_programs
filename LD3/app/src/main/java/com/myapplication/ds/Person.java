package com.myapplication.ds;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;

public class Person extends User implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;


    public Person(String username, String password, boolean isActive, LocalDate dateCreated, LocalDate dateModified, String firstName, String lastName, String email, String phoneNumber) {
        super(username, password, isActive, dateCreated, dateModified);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Person(String username, String password, String firstName, String lastName, String email, String phoneNumber) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Asmuo{" + super.toString() +
                "vardas = '" + firstName + '\'' +
                ", pavarde = '" + lastName + '\'' +
                ", el. pastas = '" + email + '\'' +
                ", telefono numeris = '" + phoneNumber + '\'' +
                '}' + '\n';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
