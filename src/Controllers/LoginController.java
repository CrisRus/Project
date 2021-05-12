package Controllers;

import DatabaseConnection.UserQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController extends Control  {  // extends control

    @FXML
    private PasswordField passwordText;
    @FXML
    private TextField emailText;
    Boolean isLoggedIn=false;
    UserQueries UQ=new UserQueries();
    @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private void signInButton(ActionEvent ae) throws IOException {
        String email1 = emailText.getText();
        String pw = passwordText.getText();
        if (!emailText.getText().isEmpty() &&!passwordText.getText().isEmpty()) {


            try{
             isLoggedIn=UQ.verifyLogin(email1,pw);
                System.out.println("isLoggedIn: " + isLoggedIn);
             if(isLoggedIn==true)
                 if(UQ.status=="admin"){

                     Node node = (Node) ae.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Admin.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();


                } else  if(isLoggedIn==true)
                    if(UQ.status=="employee"){
                        Node node = (Node) ae.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainScreen.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();


                }else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("wrong input");
                        alert.setContentText("password or username incorrect");
                        alert.show();
                    }

            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("internet connection");
                alert.setContentText("check internet connection");
                alert.show();
            }

        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("wrong input");
            alert.setContentText("please fill in both fields to continue");
            alert.show();
        }
    }




    @FXML
    private void signup(ActionEvent ae) throws IOException{
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Signup.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }



}




