package com.example.ld.fxControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

public class SimplifiedUserTableParameters {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty username = new SimpleStringProperty();
    private ComboBox role = new ComboBox(FXCollections.observableArrayList("Editor", "Student"));

    public SimplifiedUserTableParameters(SimpleIntegerProperty id, SimpleStringProperty username, ComboBox role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public SimplifiedUserTableParameters() {
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public ComboBox getRole() {
        return role;
    }

    public void setRole(ComboBox role) {
        this.role = role;
    }
}
