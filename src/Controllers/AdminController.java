package Controllers;

import Model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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

   @FXML
    private ListView<String> listOfNames;
    @FXML
    private TextField guestName;

    @FXML
    public void addName(MouseEvent mouseEvent) {
        listOfNames.getItems().add(guestName.getText());
    }

    @FXML
    public void removeName(MouseEvent mouseEvent) {
        int selectedID = listOfNames.getSelectionModel().getSelectedIndex();
        listOfNames.getItems().remove(selectedID);
    }



    public void searchByGuestName(ActionEvent actionEvent) {

    }
    @FXML
    public void backButton(javafx.event.ActionEvent ae) throws IOException {
            sceneSwitcher.changeScene(ae,"../View/admin.fxml" );
        }
    }

