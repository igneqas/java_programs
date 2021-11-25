package com.myapplication.ds;

import java.io.Serializable;
import java.time.LocalDate;

public class Course implements Serializable {
    private int id;
    private String courseName;
    private String description;
    private LocalDate dateCreated;
    //private Person creator;

    public Course(int id, String courseName, String description, LocalDate dateCreated) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public Course(String courseName, String description, LocalDate dateCreated, Person creator) {
        this.courseName = courseName;
        this.description = description;
        this.dateCreated = dateCreated;
        //this.creator = creator;
    }

    public Course(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return id + " : " + courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }


    /*public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }*/
}
