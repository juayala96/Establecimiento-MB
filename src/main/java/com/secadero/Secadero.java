package com.secadero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Secadero extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/principal.fxml"));
        AnchorPane root = loader.load();
        Scene escena = new Scene(root);
        primaryStage.setScene(escena);
        primaryStage.setTitle("Gestión Secadero");
        //Image imagen = new Image("C:/Users/juela/OneDrive/Escritorio/Ipesmi/Práctica Profecional/Establecimiento-MB/src/main/java/com/secadero/logo.png");
        //primaryStage.getIcons().add(imagen);
        primaryStage.show();
    }
}
