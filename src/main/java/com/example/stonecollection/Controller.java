package com.example.stonecollection;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    Scene scene;
    Stage stage;

    public  void redirect(ActionEvent event, String name,int v,int v1) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(name));
        scene = new Scene(fxmlLoader.load(), v, v1);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void showMessage(String message){
        Alert a=new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(message);
        a.setHeaderText("Warning");
        a.show();
    }
}
