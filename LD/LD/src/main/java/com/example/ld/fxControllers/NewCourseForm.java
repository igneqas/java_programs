package com.example.ld.fxControllers;

import com.example.ld.StartGui;
import com.example.ld.ds.Course;
import com.example.ld.ds.CourseSystem;
import com.example.ld.ds.Person;
import com.example.ld.ds.User;
import com.example.ld.hibernateControllers.CourseHibernateController;
import com.example.ld.hibernateControllers.UserCourseHibernateController;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class NewCourseForm {
    @FXML
    public TextField courseName;
    @FXML
    public TextArea courseDescription;

    private CourseSystem courseSystem;
    private User user;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    CourseHibernateController courseHibernateController = new CourseHibernateController(entityManagerFactory);
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);
    //UserCourseHibernateController userCourseHibernateController = new UserCourseHibernateController(entityManagerFactory);

    public void setCourseData(CourseSystem courseSystem, User user) {
        this.courseSystem = courseSystem;
        this.user = user;
    }

    public void createCourse(ActionEvent actionEvent) throws IOException {
        List<User> participants = new ArrayList<>();
        participants.add(user);
        List<User> admins = new ArrayList<>();
        admins.add(user);
        Course course = new Course(courseName.getText(),courseDescription.getText(), LocalDate.now(), participants, admins, (Person)user);
        //user = userHibernateController.getUserById(user.getId());
        user.getMyCourses().add(course);
        user.getMyAdminCourses().add(course);
        courseHibernateController.createCourse(course);
        //course.getParticipants().add(user);
        //course.getAdmins().add(user);
        //courseHibernateController.editCourse(course);
        //userHibernateController.editUser(user);
        //userCourseHibernateController.createUserCourse();

        //courseSystem.getCourses().add(course);
        returnToPrevious();
    }

    private void returnToPrevious() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-courses-window.fxml"));
        Parent root = fxmlLoader.load();
        MainCoursesWindow mainCoursesWindow = fxmlLoader.getController();
        mainCoursesWindow.setCourseData(courseSystem,user);
        Scene scene = new Scene(root);
        Stage stage = (Stage) courseName.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
