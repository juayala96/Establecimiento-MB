package com.secadero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Secadero extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/login.fxml"));
        AnchorPane root = loader.load();
        Scene escena = new Scene(root);
        primaryStage.setScene(escena);
        primaryStage.setTitle("Gestión Secadero");
        primaryStage.show();
    }
}
