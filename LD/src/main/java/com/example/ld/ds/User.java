package com.example.ld.ds;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private boolean isActive;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    @ManyToMany
    private List<Course> myCourses;
    @ManyToMany
    private List<Course> myAdminCourses;
    @ManyToMany
    private List<Folder> myFolders;
    @OneToMany(mappedBy = "creator", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<File> createdFiles;

    public User(int id, String username, String password, boolean isActive, LocalDate dateCreated, LocalDate dateModified) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        //this.myCourses = new ArrayList<>();
        //this.myAdminCourses = new ArrayList<>();
    }

    public User(String username, String password, boolean isActive, LocalDate dateCreated, LocalDate dateModified) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
        this.isActive = true;
        //this.myCourses = new ArrayList<>();
        //this.myAdminCourses = new ArrayList<>();
    }

    public User() {
    }

    @Override
    public String toString() {
        return  "ID = " + id + ", Vartotojo vardas = '" + username + '\'' +
                ", slaptazodis = '" + password + '\'' +
                ", paskyros aktyvumas = '" + getActivity() + "\', ";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<Course> myCourses) {
        this.myCourses = myCourses;
    }

    public List<File> getCreatedFiles() {
        return createdFiles;
    }

    public void setCreatedFiles(List<File> createdFiles) {
        this.createdFiles = createdFiles;
    }

    public List<Course> getMyAdminCourses() {
        return myAdminCourses;
    }

    public void setMyAdminCourses(List<Course> myAdminCourses) {
        this.myAdminCourses = myAdminCourses;
    }

    public List<Folder> getMyFolders() {
        return myFolders;
    }

    public void setMyFolders(List<Folder> myFolders) {
        this.myFolders = myFolders;
    }

    private String getActivity(){
        if(isActive)
            return "Aktyvi";
        else return "Neaktyvi";
    }
}
