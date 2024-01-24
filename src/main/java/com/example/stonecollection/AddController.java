package com.example.stonecollection;

import com.example.stonecollection.models.StoneCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddController extends Controller{


    @FXML
    private TextField txtField;

    @FXML
    protected void saveClick(ActionEvent event) throws Exception{
        DB db=new DB();
        StoneCollection collection=new StoneCollection(txtField.getText());
        db.addCollection(collection);

        redirect(event,"collection-view.fxml",600,400);
    }

    @FXML
    protected void cancelClick(ActionEvent event) throws IOException{

        redirect(event,"collection-view.fxml",600,400);
    }

}
