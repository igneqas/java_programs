package com.example.ld.fxControllers;

import com.example.ld.ds.Course;
import com.example.ld.ds.User;
import com.example.ld.hibernateControllers.CourseHibernateController;
import com.example.ld.hibernateControllers.UserHibernateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseUsersWindow implements Initializable {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);
    CourseHibernateController courseHibernateController = new CourseHibernateController(entityManagerFactory);

    @FXML
    public TableView<SimplifiedUserTableParameters> usersTable;
    @FXML
    public TableColumn<SimplifiedUserTableParameters, Integer> prId;
    @FXML
    public TableColumn<SimplifiedUserTableParameters, String> prUsername;
    @FXML
    public TableColumn<SimplifiedUserTableParameters, String> prRole;
    @FXML
    public Button backB;
    @FXML
    public Button saveB;

    private ObservableList<SimplifiedUserTableParameters> data = FXCollections.observableArrayList();
    Course course;

    public void setCourseParameters(Course course) {
        this.course = course;
        populateTable();
    }

    public void returnToMain(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) backB.getScene().getWindow();
        stage.close();
    }

    public void saveUsers(ActionEvent actionEvent) {
        usersTable.getItems().forEach(u -> {
            User user = userHibernateController.getUserById(u.getId());
            if (u.getRole().getSelectionModel().isSelected(0)) {
                if(course.getAdmins().stream().filter(u1 -> u1.getId() == user.getId()).findFirst().orElse(null) == null) {
                    course.getAdmins().add(user);
                    user.getMyAdminCourses().add(course);
                    userHibernateController.editUser(user);
                }
            } else {
                course = courseHibernateController.getCourseById(this.course.getId());
                User userFinal = course.getAdmins().stream().filter(u1 -> u1.getId() == u.getId()).findFirst().orElse(null);
                if(userFinal != null) {
                    userFinal.getMyAdminCourses().remove(course);
                    userHibernateController.editUser(userFinal);
                    courseHibernateController.editCourse(course);
                }
            }
        });
        Stage stage = (Stage) backB.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prId.setCellValueFactory(new PropertyValueFactory<SimplifiedUserTableParameters, Integer>("id"));
        prUsername.setCellValueFactory(new PropertyValueFactory<SimplifiedUserTableParameters, String>("username"));
        prRole.setCellValueFactory(new PropertyValueFactory<SimplifiedUserTableParameters, String>("role"));
    }

    private void populateTable() {
        course.getParticipants().forEach(u -> {
            SimplifiedUserTableParameters tableParameters = new SimplifiedUserTableParameters();
            tableParameters.setId(u.getId());
            tableParameters.setUsername(u.getUsername());
            if(course.getAdmins().contains(u)) {
                tableParameters.getRole().getSelectionModel().select(0);
            } else {
                tableParameters.getRole().getSelectionModel().select(1);
            }
            data.add(tableParameters);
        });
        usersTable.setItems(data);
    }
}
