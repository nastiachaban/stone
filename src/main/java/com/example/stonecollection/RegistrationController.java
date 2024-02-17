package com.example.stonecollection;

import com.example.stonecollection.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController extends Controller{

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    PasswordField confirmationField;

    @FXML
    public void registerClick(ActionEvent event) throws Exception{
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || confirmationField.getText().isEmpty()){
           showMessage("enter all data");
           return;
        }
        if(!passwordField.getText().equals(confirmationField.getText())){
            showMessage("confirmation was failed");
            return;
        }
        User user=new User(usernameField.getText(),passwordField.getText());

        DB db=new DB();
        db.addUser(user);
        redirect(event,"login-view.fxml",500,250);

    }

    @FXML
    public void cancelClick(ActionEvent event)throws Exception{
        redirect(event,"login-view.fxml",500,250);
    }
}
