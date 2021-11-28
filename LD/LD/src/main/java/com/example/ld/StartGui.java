package com.example.ld;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("login-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Course System");
        stage.setScene(scene);
        stage.show();
    }
}
