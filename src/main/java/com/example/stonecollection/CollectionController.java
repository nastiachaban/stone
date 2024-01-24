package com.example.stonecollection;

import com.example.stonecollection.models.StoneCollection;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CollectionController extends Controller implements Initializable  {

    @FXML
    TableView<StoneCollection> table;

    @FXML
    TableColumn<StoneCollection,String> nameCol;

    @FXML
    TableColumn<StoneCollection,String> priceCol;

    @FXML
    TableColumn<StoneCollection,String> preciousCol;

    @FXML
    TableColumn<StoneCollection,String> semipreciousCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        nameCol.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getName()));
        priceCol.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getPrice()+""));
        preciousCol.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getPreciousAmount()+""));
        semipreciousCol.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getSemiPreciousAmount()+""));
        try{
            DB db=new DB();
            ObservableList<StoneCollection> collection = FXCollections.observableArrayList(db.getCollections());
            table.setItems(collection);
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void onDeleteClick(ActionEvent event) throws Exception{
        DB.collection=table.getSelectionModel().getSelectedItem();
        redirect(event,"delete-view.fxml",300,150);
    }

    @FXML
    public void onAddClick(ActionEvent event) throws Exception{
        redirect(event,"add-view.fxml",300,150);
    }

    @FXML
    public void onEditClick(ActionEvent event) throws Exception{
        DB.collection=table.getSelectionModel().getSelectedItem();
        redirect(event,"edit-view.fxml",650,450);
    }

}
