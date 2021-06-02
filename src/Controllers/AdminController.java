package Controllers;

import Model.SceneSwitcher;
import com.sun.javafx.charts.Legend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


import javafx.fxml.Initializable;

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

    @FXML
    private ListView<String> listOfNames;

    @FXML
    private TextField guestName;





    public void searchByGuestName(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }


    @FXML
    public void addName(MouseEvent mouseEvent) {
        listOfNames.getItems().add(guestName.getText());
    }

    @FXML
    public void removeName(MouseEvent mouseEvent) {
        int selectedID= listOfNames.getSelectionModel().getSelectedIndex();
        listOfNames.getItems().remove(selectedID);
    }


    @FXML
    public void updateList(MouseEvent mouseEvent) {

    }

    @FXML
    public void clearList(MouseEvent mouseEvent) {
    }
}
