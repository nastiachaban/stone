package com.example.stonecollection;

import com.example.stonecollection.models.Color;
import com.example.stonecollection.models.Precious;
import com.example.stonecollection.models.Rarity;
import com.example.stonecollection.models.SemiPrecious;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditSemiPreciousController extends Controller implements Initializable{

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField weightTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private ComboBox<Color> colorBox;

    @FXML
    private TextField qualityTxt;

    public void onSaveClick(ActionEvent event) throws Exception{
        String name=nameTxt.getText();
        DB.stone.setName(name);
        DB.stone.setWeight(Integer.parseInt(weightTxt.getText()));
        DB.stone.setPrice(Double.parseDouble(priceTxt.getText()));
        DB.stone.setColor(colorBox.getValue());
        ((SemiPrecious)DB.stone).setQuality(Integer.parseInt(qualityTxt.getText()));

        DB db=new DB();
        db.editSemiPrecious((SemiPrecious) DB.stone);
        redirect(event,"edit-view.fxml",650,450);
    }

    @FXML

    public void onCancelClick(ActionEvent event) throws Exception{
        redirect(event,"edit-view.fxml",650,450);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameTxt.setText(DB.stone.getName());
        weightTxt.setText(DB.stone.getWeight()+"");
        priceTxt.setText(DB.stone.getPrice()+"");
        colorBox.setItems(FXCollections.observableArrayList(Color.values()));
        qualityTxt.setText(((SemiPrecious)DB.stone).getQuality()+"");
        colorBox.getSelectionModel().select(DB.stone.getColor());

    }
}
