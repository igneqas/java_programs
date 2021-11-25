package com.example.ld.fxControllers;

import com.example.ld.StartGui;
import com.example.ld.ds.File;
import com.example.ld.hibernateControllers.FileHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EditFileWindow {
    @FXML
    public TextField fileNameF;

    private File file;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    FileHibernateController fileHibernateController = new FileHibernateController(entityManagerFactory);

    public void setFileData(File file){
        this.file = file;
        fileNameF.setText(file.getFileName());
    }

    public void editFile(ActionEvent actionEvent) {
        if(MainCoursesWindow.illegalCharactersUsed(fileNameF.getText())){
            LoginWindow.alertMessage("Name can't contain any of the following characters / \\ : * ? \" < > |");
        }else {
            file.setFileName(fileNameF.getText());
            fileHibernateController.editFile(file);
            Stage stage = (Stage) fileNameF.getScene().getWindow();
            stage.close();
        }
    }
}
