package com.example.ld.ds;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseSystem implements Serializable {
    private int id;
    private ArrayList<User> users;
    private ArrayList<Course> courses;

    public CourseSystem(int id, ArrayList<User> users, ArrayList<Course> courses) {
        this.id = id;
        this.users = users;
        this.courses = courses;
    }

    public CourseSystem(ArrayList<User> users, ArrayList<Course> courses) {
        this.users = users;
        this.courses = courses;
    }

    public CourseSystem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
