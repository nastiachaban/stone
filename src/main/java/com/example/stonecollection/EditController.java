package com.example.stonecollection;

import com.example.stonecollection.models.Precious;
import com.example.stonecollection.models.SemiPrecious;
import com.example.stonecollection.models.Stone;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditController extends Controller implements Initializable {

    @FXML
    TableView<Stone> table;

    @FXML
    TextField collectionName;

    @FXML
    TableColumn<Stone, String> nameCol;

    @FXML
    TableColumn<Stone, String> priceCol;

    @FXML
    TableColumn<Stone, String> weightCol;

    @FXML
    TableColumn<Stone, String> colorCol;

    @FXML
    TableColumn<Stone, String> rarityqualityCol;

    @FXML
    ComboBox<String> sortBox;

    @FXML

    public void onAddSemiPreciousClick(ActionEvent event) throws Exception {

        redirect(event, "addsemiprecious-view.fxml", 400, 400);
    }

    @FXML

    public void onAddPreciousClick(ActionEvent event) throws Exception {
        redirect(event, "addprecious-view.fxml", 400, 400);
    }



    @FXML

    public void onEditClick(ActionEvent event) throws Exception {
        if(table.getSelectionModel().getSelectedItem()==null){
            showMessage("select stone to edit");
            return;
        }
        DB.stone = table.getSelectionModel().getSelectedItem();
        if (DB.stone instanceof SemiPrecious)
            redirect(event, "editsemiprecious-view.fxml", 400, 400);
        else
            redirect(event, "editprecious-view.fxml", 400, 400);
    }

    @FXML

    public void onDeleteClick(ActionEvent event) throws Exception {
        if(table.getSelectionModel().getSelectedItem()==null){
            showMessage("select stone to delete");
            return;
        }
        DB.stone = table.getSelectionModel().getSelectedItem();
        redirect(event, "deletestone-view.fxml", 300, 200);
    }

    @FXML

    public void onCancelClick(ActionEvent event) throws Exception {
        redirect(event, "collection-view.fxml", 600, 500);
    }

    @FXML

    public void onApplyClick(ActionEvent event) throws Exception{
        String newname=collectionName.getText().trim();
        DB.collection.setName(newname);
        DB db=new DB();
        db.editCollection(DB.collection);
        redirect(event, "collection-view.fxml", 600, 500);
    }

    public static void sortByName(ArrayList<Stone> list){
        list.sort((st1,st2)->st1.getName().compareTo(st2.getName()));
    }

    public static void sortByPrice(ArrayList<Stone> list){
        list.sort((st1,st2)->Double.compare(st1.getPrice(),st2.getPrice()));
    }
    public static void sortByWeight(ArrayList<Stone> list){
        list.sort((st1,st2)->Integer.compare(st1.getWeight(),st2.getWeight()));
    }

    public static void sortByColor(ArrayList<Stone> list){
        list.sort((st1,st2)->st1.getColor().toString().compareTo(st2.getColor().toString()));
    }

    @FXML
    public void onSortClick(){
        if(sortBox.getSelectionModel().getSelectedItem()==null){
            showMessage("choose criteria of sort");
            return;
        }
        String selectedItem=sortBox.getSelectionModel().getSelectedItem();
        try {
            DB db = new DB();
            ArrayList<Stone> list=db.getStones(DB.collection.getId());
            if(selectedItem.equals("name")){
                sortByName(list);
            }
            else if(selectedItem.equals("price")){
                sortByPrice(list);
            }
            else if(selectedItem.equals("weight")){
                sortByWeight(list);
            }
            else if(selectedItem.equals("color")){
                sortByColor(list);
            }

            ObservableList<Stone> collection = FXCollections.observableArrayList(list);
            table.setItems(collection);
            collectionName.setText(DB.collection.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        priceCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPrice() + ""));
        weightCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getWeight() + ""));
        colorCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getColor() + ""));
//        String value;
//       if(cellData.getValue() instanceof Precious){
//           value=((Precious)cellData.getValue()).getRarity()+ "";
//       }
//       else{
//           value =((SemiPrecious)cellData.getValue()).getQuality()+ "";
//       }
        rarityqualityCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper((cellData.getValue() instanceof Precious) ? ((Precious) cellData.getValue()).getRarity() + "" : ((SemiPrecious) cellData.getValue()).getQuality() + ""));
        try {
            DB db = new DB();
            ObservableList<Stone> collection = FXCollections.observableArrayList(db.getStones(DB.collection.getId()));
            table.setItems(collection);
            collectionName.setText(DB.collection.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
