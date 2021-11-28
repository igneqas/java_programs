package com.example.ld.fxControllers;

import com.example.ld.StartGui;
import com.example.ld.ds.Course;
import com.example.ld.ds.CourseSystem;
import com.example.ld.ds.Person;
import com.example.ld.ds.User;
import com.example.ld.hibernateControllers.CourseHibernateController;
import com.example.ld.hibernateControllers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private Course course;
    private CourseSystem courseSystem;
    private User user;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    CourseHibernateController courseHibernateController = new CourseHibernateController(entityManagerFactory);

    public void setCourseData(CourseSystem courseSystem, Course course, User user){
        this.courseSystem = courseSystem;
        this.course = course;
        this.user = user;
        setFields();
    }

    private void setFields() {
        courseNameF.setText(course.getCourseName());
        courseDescriptionF.setText(course.getDescription());
    }

    public void returnToMain(ActionEvent actionEvent) throws IOException {
        course.setCourseName(courseNameF.getText());
        course.setDescription(courseDescriptionF.getText());
        courseHibernateController.editCourse(course);

        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-courses-window.fxml"));
        Parent root = fxmlLoader.load();
        MainCoursesWindow mainCoursesWindow = fxmlLoader.getController();
        mainCoursesWindow.setCourseData(courseSystem, user);
        Scene scene = new Scene(root);
        Stage stage = (Stage) courseDescriptionF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}
