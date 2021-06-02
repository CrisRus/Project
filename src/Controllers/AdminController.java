package Controllers;

import Model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private SceneSwitcher sceneSwitcher;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneSwitcher = SceneSwitcher.getInstance();

    }

    @FXML
    public void logOut(ActionEvent ae) {
        sceneSwitcher.changeScene(ae, "../View/login.fxml");
    }

    @FXML
    public void toRooms(ActionEvent ae){
        sceneSwitcher.changeScene(ae, "../View/room.fxml");
    }

    @FXML
    public void makeReservation(ActionEvent ae){
        sceneSwitcher.changeScene(ae, "../View/booking.fxml");
    }

    @FXML
    public void toGuests(ActionEvent ae) {
        sceneSwitcher.changeScene(ae, "../View/guestInfo.fxml");
    }


}
