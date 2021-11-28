package com.example.ld.ds;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Person extends User implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @OneToMany(mappedBy = "creator", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Course> myCreatedCourses;

    public Person(int id, String username, String password, boolean isActive, LocalDate dateCreated, LocalDate dateModified, String firstName, String lastName, String email, String phoneNumber, List<Course> myCreatedCourses) {
        super(id, username, password, isActive, dateCreated, dateModified);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.myCreatedCourses = myCreatedCourses;
    }

    public Person(String username, String password, boolean isActive, LocalDate dateCreated, LocalDate dateModified, String firstName, String lastName, String email, String phoneNumber) {
        super(username, password, isActive, dateCreated, dateModified);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Person(String username, String password, String firstName, String lastName, String email, String phoneNumber) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Person() {
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

    public List<Course> getMyCreatedCourses() {
        return myCreatedCourses;
    }

    public void setMyCreatedCourses(List<Course> myCreatedCourses) {
        this.myCreatedCourses = myCreatedCourses;
    }
}
