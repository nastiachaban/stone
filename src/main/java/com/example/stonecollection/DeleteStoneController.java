package com.example.stonecollection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DeleteStoneController extends Controller {

    @FXML
    public void onDeleteClick(ActionEvent event) throws Exception{
        DB db=new DB();
        db.deleteStone(DB.stone);
        redirect(event,"edit-view.fxml",650,450);
    }
    @FXML
    public void onCancelClick(ActionEvent event) throws Exception{
        redirect(event,"edit-view.fxml",650,450);
    }
}
