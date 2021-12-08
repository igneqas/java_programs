package com.example.ld.fxControllers;

import com.example.ld.StartGui;
import com.example.ld.ds.*;
import com.example.ld.hibernateControllers.CourseHibernateController;
import com.example.ld.hibernateControllers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class EditCourseWindow {
    @FXML
    public TextField courseNameF;
    @FXML
    public TextArea courseDescriptionF;
    @FXML
    public Button backB;
    @FXML
    public Button saveB;

    private Course course;
    private User user;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    CourseHibernateController courseHibernateController = new CourseHibernateController(entityManagerFactory);

    public void setCourseData(Course course, User user){
        this.course = course;
        this.user = user;
        setFields();
        setEdit();
    }

    private void setEdit() {
        for (User p : courseHibernateController.getCourseById(course.getId()).getAdmins()) {
            if (p.getId() == user.getId() || user.getUserRole() == Role.SYSTEM_ADMIN) {
                return;
            }
        }
        courseNameF.setEditable(false);
        courseDescriptionF.setEditable(false);
        saveB.setVisible(false);
    }

    private void setFields() {
        courseNameF.setText(course.getCourseName());
        courseDescriptionF.setText(course.getDescription());
    }

    public void returnToMain(ActionEvent actionEvent) throws IOException {
        if(MainCoursesWindow.illegalCharactersUsed(courseNameF.getText())){
            LoginWindow.alertMessage("Name can't contain any of the following characters / \\ : * ? \" < > |");
        } else {
            course.setCourseName(courseNameF.getText());
            course.setDescription(courseDescriptionF.getText());
            courseHibernateController.editCourse(course);

            changeWindow();
        }
    }

    public void returnToMainWindow(ActionEvent actionEvent) throws IOException {
        changeWindow();
    }

    private void changeWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-courses-window.fxml"));
        Parent root = fxmlLoader.load();
        MainCoursesWindow mainCoursesWindow = fxmlLoader.getController();
        mainCoursesWindow.setCourseData(user);
        Scene scene = new Scene(root);
        Stage stage = (Stage) courseDescriptionF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
