package com.example.stonecollection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DeleteController extends Controller{

    @FXML
    public void onDeleteClick(ActionEvent event) throws Exception{

        DB db=new DB();
        db.deleteCollection(DB.collection);
        redirect(event,"collection-view.fxml",600,400);
    }
    @FXML
    public void onCancelClick(ActionEvent event) throws Exception{
        redirect(event,"collection-view.fxml",600,400);
    }



}
