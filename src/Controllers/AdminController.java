package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML public void torooms(ActionEvent ae )throws IOException {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/room.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML public void makereservation (ActionEvent ae )throws IOException {


        Node node = (Node) ae.getSource();
    Stage stage = (Stage) node.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/booking.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
}


}
