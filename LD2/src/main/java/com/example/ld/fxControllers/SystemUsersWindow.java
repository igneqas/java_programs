package com.example.ld.fxControllers;

import com.example.ld.StartGui;
import com.example.ld.ds.Company;
import com.example.ld.ds.Person;
import com.example.ld.ds.Role;
import com.example.ld.ds.User;
import com.example.ld.hibernateControllers.UserHibernateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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


public class SystemUsersWindow implements Initializable {



    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);

    @FXML
    public Button saveB;
    @FXML
    public Button backB;
    @FXML
    public TableColumn<TableParameters, Integer> prId;
    @FXML
    public TableColumn<TableParameters, String> prUsername;
    @FXML
    public TableColumn<TableParameters, String> prRole;
    @FXML
    public TableColumn<TableParameters, String> prFirstName;
    @FXML
    public TableColumn<TableParameters, String> prLastName;
    @FXML
    public TableColumn<TableParameters, String> prEmail;
    @FXML
    public TableColumn<TableParameters, String> prNumber;
    @FXML
    public TableColumn<TableParameters, String> prCompanyName;
    @FXML
    public TableColumn<TableParameters, String> prRepresentative;
    @FXML
    public TableView<TableParameters> usersTable;

    private ObservableList<TableParameters> data = FXCollections.observableArrayList();

    public void returnToMain(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) backB.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prId.setCellValueFactory(new PropertyValueFactory<TableParameters, Integer>("id"));
        prUsername.setCellValueFactory(new PropertyValueFactory<TableParameters, String>("username"));
        prFirstName.setCellValueFactory(new PropertyValueFactory<TableParameters, String>("firstName"));
        prLastName.setCellValueFactory(new PropertyValueFactory<TableParameters, String>("lastName"));
        prEmail.setCellValueFactory(new PropertyValueFactory<TableParameters, String>("email"));
        prNumber.setCellValueFactory(new PropertyValueFactory<TableParameters, String>("phoneNumber"));
        prCompanyName.setCellValueFactory(new PropertyValueFactory<TableParameters, String>("companyName"));
        prRepresentative.setCellValueFactory(new PropertyValueFactory<TableParameters, String>("representative"));
        prRole.setCellValueFactory(new PropertyValueFactory<TableParameters, String>("role"));
        populateTable();
    }

    private void populateTable() {
        userHibernateController.getAllUsers().forEach(u -> {
            TableParameters tableParameters = new TableParameters();
            tableParameters.setId(u.getId());
            tableParameters.setUsername(u.getUsername());
            if(u instanceof Person) {
                u = (Person) u;
                tableParameters.setFirstName(((Person) u).getFirstName());
                tableParameters.setLastName(((Person) u).getLastName());
                tableParameters.setEmail(((Person) u).getEmail());
                tableParameters.setPhoneNumber(((Person) u).getPhoneNumber());
            } else {
                u = (Company) u;
                tableParameters.setCompanyName((((Company) u).getCompanyName()));
                tableParameters.setRepresentative(((Company) u).getRepresentative());
            }

            switch (u.getUserRole()) {
                case SYSTEM_ADMIN:
                    tableParameters.getRole().getSelectionModel().select(0);
                    break;
                case COURSE_CREATOR:
                    tableParameters.getRole().getSelectionModel().select(1);
                    break;
                case REGULAR_USER:
                    tableParameters.getRole().getSelectionModel().select(2);
                    break;
            }
            if(u instanceof Company) {
                System.out.println("ADSDASDAS");
                tableParameters.getRole().setDisable(true);
            }
            data.add(tableParameters);
        });
        usersTable.setItems(data);
    }

    public void saveUsers(ActionEvent actionEvent) {
        usersTable.getItems().forEach(u -> {
            User user = userHibernateController.getUserById(u.getId());
            if (u.getRole().getSelectionModel().isSelected(0)) {
                user.setUserRole(Role.SYSTEM_ADMIN);
            } else if (u.getRole().getSelectionModel().isSelected(1)) {
                user.setUserRole(Role.COURSE_CREATOR);
            } else {
                user.setUserRole(Role.REGULAR_USER);
            }
            userHibernateController.editUser(user);
        });

        Stage stage = (Stage) backB.getScene().getWindow();
        stage.close();
    }
}
