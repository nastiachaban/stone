package com.example.stonecollection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends Controller{

    @FXML
    TextField usernameTxt;

    @FXML
    PasswordField passwordTxt;

    @FXML
    protected void loginClick(ActionEvent event) throws Exception{
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String bCryptedPassword = bCryptPasswordEncoder.encode("password");
//        boolean passwordIsValid = bCryptPasswordEncoder.matches("password", bCryptedPassword);


                DB db=new DB();
        String userN= db.getPasswordByUsername(usernameTxt.getText());
        if(userN.equals(passwordTxt.getText())){
            redirect(event,"collection-view.fxml",600,500);;
        }else
            showMessage("invalid data");

    }
}
