package com.example.ld.ds;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String courseName;
    private String description;
    private LocalDate dateCreated;
    @OneToMany(mappedBy = "parentCourse", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Folder> courseFolders;
    @ManyToMany(mappedBy = "myCourses", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> participants;
    @ManyToMany(mappedBy = "myAdminCourses", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> admins;
    @ManyToOne
    private Person creator;

    public Course(String courseName, String description, LocalDate dateCreated, Person creator) {
        this.courseName = courseName;
        this.description = description;
        this.dateCreated = dateCreated;
        this.creator = creator;
    }

    public Course(String courseName, String description, List<User> participants, List<User> admins, Person creator) {
        this.courseName = courseName;
        this.description = description;
        this.dateCreated = LocalDate.now();
        this.participants = participants;
        this.admins = admins;
        this.creator = creator;
    }

    public Course(int id, String courseName, String description, ArrayList<Folder> courseFolders, LocalDate dateCreated, ArrayList<User> participants, ArrayList<User> admins, Person creator) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.courseFolders = courseFolders;
        this.dateCreated = dateCreated;
        this.participants = participants;
        this.admins = admins;
        this.creator = creator;
    }

    public Course(String courseName, String description, ArrayList<Folder> courseFolders, LocalDate dateCreated, ArrayList<User> participants, ArrayList<User> admins, Person creator) {
        this.courseName = courseName;
        this.description = description;
        this.courseFolders = courseFolders;
        this.dateCreated = dateCreated;
        this.participants = participants;
        this.admins = admins;
        this.creator = creator;
    }

    public Course(String courseName, String description, ArrayList<Folder> courseFolders, LocalDate dateCreated) {
        this.courseName = courseName;
        this.description = description;
        this.courseFolders = courseFolders;
        this.dateCreated = dateCreated;
    }

    public Course(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Kursas{ " + "ID = \'" + id + '\'' +
                ", kurso pavadinimas = '" + courseName + '\'' +
                ", aprasymas='" + description + '\'' +
                ", sukurimo_data='" + dateCreated + '\'' +
                "} \n";
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

    public List<Folder> getCourseFolders() {
        return courseFolders;
    }

    public void setCourseFolders(List<Folder> courseFolders) {
        this.courseFolders = courseFolders;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }
}
