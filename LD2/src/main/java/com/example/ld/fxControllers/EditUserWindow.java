package com.example.ld.fxControllers;

import com.example.ld.StartGui;
import com.example.ld.ds.Company;
import com.example.ld.ds.CourseSystem;
import com.example.ld.ds.Person;
import com.example.ld.ds.User;
import com.example.ld.fxControllers.MainCoursesWindow;
import com.example.ld.hibernateControllers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.time.LocalDate;


public class EditUserWindow {

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
    public TextField representativeF;
    @FXML
    public TextField passwordF;
    @FXML
    public Label firstNameL;
    @FXML
    public Label compNameL;
    @FXML
    public Label lastNameL;
    @FXML
    public Label repL;
    @FXML
    public Label emailL;
    @FXML
    public Label numberL;

    private User user;
    private CourseSystem courseSystem;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);

    public void setUserData(User user){
        this.user=user;
        enableFields(user);
    }

    private void enableFields(User user) {
        if(user instanceof Person){
            companyNameF.setVisible(false);
            representativeF.setVisible(false);
            compNameL.setVisible(false);
            repL.setVisible(false);
            passwordF.setText(user.getPassword());
            personFirstNameF.setText(((Person) user).getFirstName());
            personLastNameF.setText(((Person) user).getLastName());
            personEmailF.setText(((Person) user).getEmail());
            personNumberF.setText(((Person) user).getPhoneNumber());
        }
        else{
            personFirstNameF.setVisible(false);
            personLastNameF.setVisible(false);
            personEmailF.setVisible(false);
            personNumberF.setVisible(false);
            firstNameL.setVisible(false);
            lastNameL.setVisible(false);
            emailL.setVisible(false);
            numberL.setVisible(false);
            passwordF.setText(user.getPassword());
            companyNameF.setText(((Company) user).getCompanyName());
            representativeF.setText(((Company) user).getRepresentative());
        }
    }


    public void saveUser(ActionEvent actionEvent) throws IOException {
        if(user instanceof Person){
            ((Person) user).setFirstName(personFirstNameF.getText());
            ((Person) user).setLastName(personLastNameF.getText());
            ((Person) user).setEmail(personEmailF.getText());
            ((Person) user).setPhoneNumber(personNumberF.getText());
        }else{
            ((Company) user).setCompanyName(companyNameF.getText());
            ((Company) user).setRepresentative(representativeF.getText());
        }
        user.setPassword(passwordF.getText());
        user.setDateModified(LocalDate.now());
        userHibernateController.editUser(user);

        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-courses-window.fxml"));
        Parent root = fxmlLoader.load();
        MainCoursesWindow mainCoursesWindow = fxmlLoader.getController();
        mainCoursesWindow.setCourseData(user);
        Scene scene = new Scene(root);
        Stage stage = (Stage) personNumberF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
