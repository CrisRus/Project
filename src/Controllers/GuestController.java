package Controllers;

import DatabaseConnection.UserQueries;
import Model.Guest;

import Model.Room;
import Model.SceneSwitcher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.layout.StackPane;


import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GuestController implements Initializable {
    UserQueries ub;

    String name;
    @FXML
    javafx.scene.control.TextField search,guestName;
    private SceneSwitcher sceneSwitcher;
    @FXML
    public void toGuests(ActionEvent ae) throws IOException {
        sceneSwitcher.changeScene(ae, "../View/guest.fxml");
    }


    @FXML
    public ListView<String> listOfNames;
    @FXML
    private StackPane stackpane;
    @FXML
    private ComboBox comb;






    public void initialize(URL location, ResourceBundle resources) {
        ub = new UserQueries();
        String entrySql = "SELECT * FROM cusbooking";
        fillGuests(entrySql);
        sceneSwitcher = SceneSwitcher.getInstance();



    }

    public void fillGuests(String sql) {
        listOfNames.getItems().clear();



        try {
            PreparedStatement preparedStatement = ub.connection.prepareStatement(sql);


            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {



                listOfNames.getItems().addAll(rs.getString("firstname")+
                        "-         " + rs.getString("startdate") + "-         " +
                        rs.getString("enddate") +
                        "   -           " + rs.getString("roomcode") +
                        " -           ");


            }
        } catch (SQLException ex) {

        }
    }

    @FXML
    public void back(javafx.event.ActionEvent ae) throws IOException {
        sceneSwitcher.changeScene(ae,"../View/admin.fxml" );
    }



    @FXML
    private void searchByGuestName(ActionEvent ae) {
        if (search.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("please enter the first name of the guest");
            alert.setContentText("enter guest firstname");
            alert.show();
        } else {

            try {
                fillGuests("SELECT * FROM cusbooking WHERE firstname ='" + search.getText().toString().trim() + "'");
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("please enter a valid firstname");
                alert.setContentText("enter valid firstname please");
                alert.show();
            }
        }
    }
    @FXML
    private void Delete(ActionEvent ae) {
        if (guestName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("please enter the room code");
            alert.setContentText("enter the correct room code and search");
            alert.show();
        } else {

            try {
                fillGuests("DELETE FROM cusbooking  WHERE firstname ='" + guestName.getText().toString().trim() + "'");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("A deletion was made");
                alert.setContentText("Guest" +guestName.getText() +"    was deleted");
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("There is no guest with the above name");
                alert.setContentText("please enter correct guest firstname");
                alert.show();
            }
        }


    }


}








