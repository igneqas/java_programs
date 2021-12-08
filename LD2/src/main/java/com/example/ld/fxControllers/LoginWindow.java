package com.example.ld.fxControllers;

import com.example.ld.StartGui;
import com.example.ld.ds.Course;
import com.example.ld.ds.CourseSystem;
import com.example.ld.ds.User;
import com.example.ld.control.Constants;
import com.example.ld.hibernateControllers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginWindow implements Initializable {
    @FXML
    public TextField loginF;
    @FXML
    public PasswordField passwordF;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);

    public void validateAndLoadCourses(ActionEvent actionEvent) throws IOException {
        if(loginF.getText().equals("") || passwordF.getText().equals("")) {
            alertMessage("Login fields cannot be empty");
            return;
        }
        User user = userHibernateController.getAllUsers().stream().filter(u -> u.getUsername().equals(loginF.getText())).filter(u -> u.getPassword().equals(passwordF.getText())).findFirst().orElse(null);
        if(user != null){
            FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-courses-window.fxml"));
            Parent root = fxmlLoader.load();
            MainCoursesWindow mainCoursesWindow = fxmlLoader.getController();
            mainCoursesWindow.setCourseData(user);
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginF.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }else{
            alertMessage("Wrong input, no such user found");
        }
    }

    public static void alertMessage(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

    public void openSignupForm(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("sign-up-form.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) loginF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
