package com.example.stonecollection;

import com.example.stonecollection.models.Color;
import com.example.stonecollection.models.Precious;
import com.example.stonecollection.models.SemiPrecious;
import com.example.stonecollection.models.Stone;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddSemipreciousController extends Controller implements Initializable {

    @FXML
    TextField nameTxt;

    @FXML
    TextField priceTxt;

    @FXML
    TextField weightTxt;

    @FXML
    ComboBox<Color> colorBox;

    @FXML
    TextField qualityTxt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorBox.setItems(FXCollections.observableArrayList(Color.values()));
    }

    @FXML
    protected void onCancelClick(ActionEvent event) throws IOException {
        redirect(event,"edit-view.fxml",650,450);
    }

    @FXML
    protected void onSaveClick(ActionEvent event) throws Exception{
        String name=nameTxt.getText();
        int weight=Integer.parseInt(weightTxt.getText());
        double price=Double.parseDouble(priceTxt.getText());
        Color color=colorBox.getValue();
        int quality=Integer.parseInt(qualityTxt.getText());

        SemiPrecious stone=new SemiPrecious(name,price,color,weight,quality,DB.collection.getId());
        DB db=new DB();
        db.addSemiPrecious(stone);
        redirect(event,"edit-view.fxml",650,450);

    }
}

