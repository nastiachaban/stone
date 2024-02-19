package com.example.stonecollection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HelloController extends Controller {
    @FXML
    protected void startClick(ActionEvent event) throws IOException {
            redirect(event,"login-view.fxml",500,250);
    }

}