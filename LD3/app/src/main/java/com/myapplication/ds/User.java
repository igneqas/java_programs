package com.myapplication.ds;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private boolean isActive;
    private LocalDate dateCreated;
    private LocalDate dateModified;

    public User(int id, String username, String password, boolean isActive, LocalDate dateCreated, LocalDate dateModified) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public User(String username, String password, boolean isActive, LocalDate dateCreated, LocalDate dateModified) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
        this.isActive = true;
    }

    public User() {
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

    private String getActivity(){
        if(isActive)
            return "Aktyvi";
        else return "Neaktyvi";
    }

    @Override
    public String toString() {
        return  "ID = " + id + ", Vartotojo vardas = '" + username + '\'' +
                ", slaptazodis = '" + password + '\'' +
                ", paskyros aktyvumas = '" + getActivity() + "\', ";
    }
}
