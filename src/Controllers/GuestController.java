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
    @FXML
    javafx.scene.control.TextField search;
    private SceneSwitcher sceneSwitcher;
    @FXML
    public void toGuests(ActionEvent ae) {
        sceneSwitcher.changeScene(ae, "../View/guest.fxml");
    }


    @FXML
    public ListView<String> listView;
    @FXML
    private StackPane stackpane;
    @FXML
    private ComboBox comb;


    String s = comb.getSelectionModel().getSelectedItem().toString();



    public void initialize(URL location, ResourceBundle resources) {
        ub = new UserQueries();
        String entrySql = "SELECT * FROM guest";
        fillGuests(entrySql);
        sceneSwitcher = SceneSwitcher.getInstance();

        ObservableList<String> list= FXCollections.observableArrayList("Single","Family","AC","Non AC");
        comb.setItems(list);

    }

    public void fillGuests(String sql) {
        listView.getItems().clear();



        try {
            PreparedStatement preparedStatement = ub.connection.prepareStatement(sql);


            ResultSet rs = preparedStatement.executeQuery();
            // Guest(String firstName, String lastName, String email, String address, String phone, String roomType, String roomCode,
            //                 String startDate, String endDate, String services
            while (rs.next()) {

                Main.guestobject = new Guest(rs.getString("Name"), rs.getString("lastName"),
                        rs.getString("email"), rs.getString("address"), rs.getString("phone")
                        , rs.getString("roomType"), rs.getString("roomCode"), rs.getString("startDate")
                        ,  rs.getString("endDate")
                        ,  rs.getString("services")
                );
                listView.getItems().addAll(Main.guestobject.getRoomCode() +
                        "                                -             " +
                        Main.guestobject.getFirstName() + "                                     -  " +
                        Main.guestobject.getLastName() +
                        "                         -  " + Main.guestobject.getEmail() +
                        "                         -  " + Main.guestobject.getRoomCode() + "                                   -  ");


            }
        } catch (SQLException ex) {

        }
    }

    @FXML
    public void back(javafx.event.ActionEvent ae) throws IOException {
        sceneSwitcher.changeScene(ae,"../View/admin.fxml" );
    }

    @FXML
    private void close(ActionEvent event) throws IOException {

    }

    @FXML
    private void searchByGuestName(ActionEvent ae) {
        if (search.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("please enter the guest code");
            alert.setContentText("enter the correct guest code and search");
            alert.show();
        } else {

            try {
                // fillGuests("SELECT * FROM guest WHERE guestCode ='" + search.getText().toString().trim() + "'");
                fillGuests("SELECT * FROM guest WHERE 1" );
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("please enter a valid guest code");
                alert.setContentText("enter the correct guest code and search");
                alert.show();
            }
        }


    }
    @FXML
    private void makeUnAvailable(ActionEvent ae) {

        String text=search.getText().toString().trim();
        int res=0;
        String sql="UPDATE guest SET guestStatus=? WHERE guestCode=?";
        try {
            PreparedStatement ps=(PreparedStatement)ub.connection.prepareStatement(sql);
            ps.setString(1, "unavailable");
            ps.setString(2, text);

            res=ps.executeUpdate();




        } catch (SQLException ex) {
            ex.printStackTrace();        }

        if(res>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data update");
            alert.setHeaderText("Information Dialog");
            alert.setContentText("Record updated successfully!");
            alert.showAndWait();
            fillGuests("SELECT * FROM `guest` WHERE 1");
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data update");
            alert.setHeaderText("Information Dialog");
            alert.setContentText("Error!");
            alert.showAndWait();
        }
    }
    @FXML
    private void search(ActionEvent event) {

        fillGuests("SELECT * FROM Guest WHERE ");
    }

}



