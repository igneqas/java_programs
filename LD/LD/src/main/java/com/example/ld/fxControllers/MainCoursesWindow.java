package com.example.ld.fxControllers;

import com.example.ld.StartGui;
import com.example.ld.control.RW;
import com.example.ld.ds.Course;
import com.example.ld.ds.CourseSystem;
import com.example.ld.ds.Person;
import com.example.ld.ds.User;
import com.example.ld.hibernateControllers.CourseHibernateController;
import com.example.ld.hibernateControllers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainCoursesWindow implements Initializable {
    @FXML
    public ListView myCourses;
    @FXML
    public TreeView courseFiles;

    private CourseSystem courseSystem;
    private User user;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);
    CourseHibernateController courseHibernateController = new CourseHibernateController(entityManagerFactory);

    public void setCourseData(CourseSystem courseSystem, User user) {
        this.courseSystem = courseSystem;
        this.user = user;
        fillTables();
    }

    private void fillTables() {
        myCourses.getItems().clear();
        for(Course c : courseSystem.getCourses()){
            if(c.getParticipants().contains(user)){
                myCourses.getItems().add(c.getCourseName());
            }
        }
        myCourses.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveToFile(ActionEvent actionEvent) {
        RW.writeToFile("out.txt", courseSystem);
    }

    public void openNewCourseForm(ActionEvent actionEvent) throws IOException {
        if(user instanceof Person) {
            FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("new-course-form.fxml"));
            Parent root = fxmlLoader.load();
            NewCourseForm newCourseForm = fxmlLoader.getController();
            newCourseForm.setCourseData(courseSystem, user);
            Scene scene = new Scene(root);
            Stage stage = (Stage) myCourses.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }else{
            LoginWindow.alertMessage("You are not an individual");
        }
    }

    public void openEditCourseWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("edit-course-window.fxml"));
        Parent root = fxmlLoader.load();
        EditCourseWindow editCourseWindow = fxmlLoader.getController();
        editCourseWindow.setCourseData(courseSystem, selectCourse(), user);
        Scene scene = new Scene(root);
        Stage stage = (Stage) myCourses.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void deleteCourse(ActionEvent actionEvent) {
        courseHibernateController.removeCourse(selectCourse().getId());
    }

    public void deleteUser(ActionEvent actionEvent) throws IOException {
        userHibernateController.removeUser(user.getId());
        LoginWindow.alertMessage("Account was deleted");
        toLoginWindow();
    }

    public void editUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("edit-user-window.fxml"));
        Parent root = fxmlLoader.load();
        EditUserWindow editUserWindow = fxmlLoader.getController();
        editUserWindow.setUserData(courseSystem, user);
        Scene scene = new Scene(root);
        Stage stage = (Stage) myCourses.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void returnToLogin(ActionEvent actionEvent) throws IOException {
        user = null;
        toLoginWindow();
    }

    private void toLoginWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("login-window.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) myCourses.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void openCourseUsersWindow(ActionEvent actionEvent) {
    }

    private Course selectCourse(){
        String courseNameTemp = myCourses.getSelectionModel().getSelectedItem().toString();
        Course courseTemp = courseHibernateController.getAllCourses().stream().filter(u -> u.getCourseName().equals(courseNameTemp)).findFirst().orElse(null);
        return courseTemp;
    }
}
