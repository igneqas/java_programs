package com.example.ld.fxControllers;

import com.example.ld.ds.Folder;
import com.example.ld.hibernateControllers.FolderHibernateController;
import com.example.ld.hibernateControllers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EditFolderWindow {
    @FXML
    public TextField folderNameF;

    private Folder folder;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    FolderHibernateController folderHibernateController = new FolderHibernateController(entityManagerFactory);

    public void setFolderData(Folder folder){
        this.folder = folder;
        folderNameF.setText(folder.getFolderName());
    }

    public void editFolder(ActionEvent actionEvent) {
        if(MainCoursesWindow.illegalCharactersUsed(folderNameF.getText())) {
            LoginWindow.alertMessage("Name can't contain any of the following characters / \\ : * ? \" < > |");
        }else {
            folder.setFolderName(folderNameF.getText());
            folderHibernateController.editFolder(folder);
            Stage stage = (Stage) folderNameF.getScene().getWindow();
            stage.close();
        }
    }
}
