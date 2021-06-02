package Controllers;

import DatabaseConnection.UserQueries;
import Model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Control {  // extends control

    @FXML
    private PasswordField passwordText;
    @FXML
    private TextField emailText;
    Boolean isLoggedIn = false;
    private UserQueries UQ = new UserQueries();

    private SceneSwitcher sceneSwitcher;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneSwitcher = SceneSwitcher.getInstance();
    }

    @FXML
    private void signInButton(ActionEvent ae) throws IOException {
        String email1 = emailText.getText();
        String pw = passwordText.getText();
        if (!emailText.getText().isEmpty() && !passwordText.getText().isEmpty()) {
            try {
                isLoggedIn = UQ.verifyLogin(email1, pw);
                System.out.println("isLoggedIn: " + isLoggedIn);
                if (isLoggedIn) {
                    if (UQ.status.equalsIgnoreCase("admin")|| UQ.status.equalsIgnoreCase("employee")) {
                        sceneSwitcher.changeScene(ae, "../View/admin.fxml");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("wrong input");
                        alert.setContentText("password or username incorrect");
                        alert.show();
                    }
                }
            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("internet connection");
                alert.setContentText("check internet connection");
                alert.show();
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("wrong input");
            alert.setContentText("please fill in both fields to continue");
            alert.show();
        }
    }

    @FXML
    private void signInAsGuest(ActionEvent ae) throws IOException {
        String email1 = emailText.getText();
        String pw = passwordText.getText();
        if (!emailText.getText().isEmpty() && !passwordText.getText().isEmpty()) {


            try {
                isLoggedIn = UQ.verifyGuestLogin(email1, pw);
                System.out.println("isLoggedIn: " + isLoggedIn);
                if (isLoggedIn) {

                    sceneSwitcher.changeScene(ae,"../View/guest.fxml" );
                } else {
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
    private void signup(ActionEvent ae){
        sceneSwitcher.changeScene(ae,"../View/signUp.fxml" );
    }


}




