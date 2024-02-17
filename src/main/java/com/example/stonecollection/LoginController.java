package com.example.stonecollection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginController extends Controller{

    @FXML
    TextField usernameTxt;

    @FXML
    PasswordField passwordTxt;

    @FXML
    protected void signupClick(ActionEvent event) throws Exception{
        redirect(event,"registration-view.fxml",600,350);
    }

    @FXML
    protected void loginClick(ActionEvent event) throws Exception{
        String passwordToHash = passwordTxt.getText();
        String generatedPassword = null;

        try
        {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(passwordToHash.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);
                DB db=new DB();
        String userN= db.getPasswordByUsername(usernameTxt.getText());
        if(userN.equals(generatedPassword)){
            redirect(event,"collection-view.fxml",600,500);
        }else
            showMessage("invalid data");

    }
}
