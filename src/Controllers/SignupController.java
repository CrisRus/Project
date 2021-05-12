package Controllers;

import DatabaseConnection.UserQueries;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController  implements Initializable {
    @FXML
    private javafx.scene.control.TextField emailtxt;
    @FXML
    private javafx.scene.control.TextField firstnametxt;
    @FXML
    private javafx.scene.control.TextField passwordtxt;
    @FXML
    private javafx.scene.control.TextField lastnametxt;
    @FXML
    private javafx.scene.control.TextField phonenumbertxt;
    UserQueries UQ = new UserQueries();

@FXML
    public void register(ActionEvent ae) throws IOException {


        if (emailtxt.getText().isEmpty() || firstnametxt.getText().isEmpty() || passwordtxt.getText().isEmpty() || lastnametxt.getText().isEmpty() || phonenumbertxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter in all fields");
            alert.showAndWait();
            return;

        }


        try {
            Main.userobject = new User(emailtxt.getText(), firstnametxt.getText(), passwordtxt.getText(), lastnametxt.getText(), phonenumbertxt.getText());
            UQ.createUser(Main.userobject);
            Node node = (Node) ae.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void tologin(ActionEvent ae) throws IOException{
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }




        @Override
            public void initialize (URL location, ResourceBundle resources){

            }
        }


