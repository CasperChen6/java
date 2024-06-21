package com.example.assign1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static javafx.application.Application.launch;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("linechartview.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Renewable Energy Share");
        stage.setScene(scene);
        stage.show();
    }
}
