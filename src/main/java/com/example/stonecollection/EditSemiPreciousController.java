package com.example.stonecollection;

import com.example.stonecollection.models.Color;
import com.example.stonecollection.models.SemiPrecious;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
        if(nameTxt.getText().equals("") || weightTxt.getText().equals("") || priceTxt.getText().equals("") || colorBox.getValue()==null || qualityTxt.getText().equals("")){
            showMessage("enter all data");
            return;
        }

        String name=nameTxt.getText();
        int weight;
        double price;
        int quality;
        try {
            weight = Integer.parseInt(weightTxt.getText());
            price = Double.parseDouble(priceTxt.getText());
            quality=Integer.parseInt(qualityTxt.getText());
        }
        catch(Exception e){
            showMessage("invalid data");
            return;
        }
        DB.stone.setName(name);
        DB.stone.setWeight(weight);
        DB.stone.setPrice(price);
        DB.stone.setColor(colorBox.getValue());
        ((SemiPrecious)DB.stone).setQuality(quality);

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
