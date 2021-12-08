package com.example.ld.fxControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

public class TableParameters {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty username = new SimpleStringProperty();
    private SimpleStringProperty firstName = new SimpleStringProperty();
    private SimpleStringProperty lastName = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty phoneNumber = new SimpleStringProperty();
    private SimpleStringProperty companyName = new SimpleStringProperty();
    private SimpleStringProperty representative = new SimpleStringProperty();
    private ComboBox role = new ComboBox(FXCollections.observableArrayList("System admin", "Creator", "Student"));

    public TableParameters(SimpleIntegerProperty id, SimpleStringProperty username, SimpleStringProperty firstName, SimpleStringProperty lastName, SimpleStringProperty email, SimpleStringProperty phoneNumber, SimpleStringProperty companyName, SimpleStringProperty representative, ComboBox role) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.representative = representative;
        //this.role = new ComboBox();
    }

    public TableParameters() {
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

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public SimpleStringProperty companyNameProperty() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public String getRepresentative() {
        return representative.get();
    }

    public SimpleStringProperty representativeProperty() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative.set(representative);
    }

    public ComboBox getRole() {
        return role;
    }

    public void setRole(ComboBox role) {
        this.role = role;
    }
}
