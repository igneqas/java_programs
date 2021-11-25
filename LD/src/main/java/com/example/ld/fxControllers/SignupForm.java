package com.example.ld.fxControllers;

import com.example.ld.StartGui;
import com.example.ld.control.RW;
import com.example.ld.ds.Company;
import com.example.ld.ds.CourseSystem;
import com.example.ld.ds.Person;
import com.example.ld.hibernateControllers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupForm implements Initializable {
    @FXML
    public TextField loginF;
    @FXML
    public PasswordField passwordF;
    @FXML
    public RadioButton radioP;
    @FXML
    public RadioButton radioC;
    @FXML
    public TextField personFirstNameF;
    @FXML
    public TextField personLastNameF;
    @FXML
    public TextField personEmailF;
    @FXML
    public TextField personNumberF;
    @FXML
    public TextField companyNameF;
    @FXML
    public TextField companyRepF;
    @FXML
    public ToggleGroup userType;

    private CourseSystem courseSystem;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);

    public void setCourseSystem(CourseSystem courseSystem) {
        this.courseSystem = courseSystem;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        companyNameF.setDisable(true);
        companyRepF.setDisable(true);
    }

    public void createUser(ActionEvent actionEvent) throws IOException {
        if(radioP.isSelected()){
            Person person = new Person(loginF.getText(),passwordF.getText(),personFirstNameF.getText(),personLastNameF.getText(),personEmailF.getText(),personNumberF.getText());
            userHibernateController.createUser(person);
        }
        else{
            Company company = new Company(loginF.getText(),passwordF.getText(),companyNameF.getText(),companyRepF.getText());
            userHibernateController.createUser(company);
        }
        //RW.writeToFile("out.txt",courseSystem);
        LoginWindow.alertMessage("User successfully created");
        returnToPrevious();
    }

    private void returnToPrevious() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("login-window.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) loginF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void returnToLogin(ActionEvent actionEvent) throws IOException {
        returnToPrevious();
    }

    public void enableFields(ActionEvent actionEvent) {
        if(radioP.isSelected()){
            personFirstNameF.setDisable(false);
            personLastNameF.setDisable(false);
            personEmailF.setDisable(false);
            personNumberF.setDisable(false);
            companyNameF.setDisable(true);
            companyRepF.setDisable(true);
        }
        else{
            personFirstNameF.setDisable(true);
            personLastNameF.setDisable(true);
            personEmailF.setDisable(true);
            personNumberF.setDisable(true);
            companyNameF.setDisable(false);
            companyRepF.setDisable(false);
        }
    }
}
