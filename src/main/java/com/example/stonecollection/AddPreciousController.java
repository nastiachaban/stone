package com.example.stonecollection;

import com.example.stonecollection.models.Color;
import com.example.stonecollection.models.Precious;
import com.example.stonecollection.models.Rarity;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPreciousController extends Controller implements Initializable {

    @FXML
    TextField nameTxt;

    @FXML
    TextField priceTxt;

    @FXML
    TextField weightTxt;

    @FXML
    ComboBox<Color> colorBox;

    @FXML
    ComboBox<Rarity> rarityBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorBox.setItems(FXCollections.observableArrayList(Color.values()));
        rarityBox.setItems(FXCollections.observableArrayList(Rarity.values()));
    }

    @FXML
    protected void onCancelClick(ActionEvent event) throws IOException {
        redirect(event,"edit-view.fxml",650,450);
    }

    @FXML
    protected void onSaveClick(ActionEvent event) throws Exception{

        if(nameTxt.getText().equals("") || weightTxt.getText().equals("") || priceTxt.getText().equals("") || colorBox.getValue()==null || rarityBox.getValue()==null){
            showMessage("enter all data");
            return;
        }
        String name=nameTxt.getText();
        int weight;
        double price;
        try{
        weight=Integer.parseInt(weightTxt.getText());
        price=Double.parseDouble(priceTxt.getText());
        }
        catch(Exception e){
            showMessage("invalid data");
            return;
        }
        Color color=colorBox.getValue();
        Rarity rarity=rarityBox.getValue();



        Precious stone=new Precious(name,price,color,weight,rarity,DB.collection.getId());
        DB db=new DB();
        db.addPrecious(stone);
        redirect(event,"edit-view.fxml",650,450);

    }
}
