package com.example.stonecollection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController extends Controller {
    @FXML
    protected void startClick(ActionEvent event) throws IOException {
            redirect(event,"collection-view.fxml",600,400);
    }

}