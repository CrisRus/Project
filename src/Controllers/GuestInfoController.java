package Controllers;

import DatabaseConnection.UserQueries;
import Model.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GuestInfoController implements Initializable {
    @FXML
    public ListView<String> listViewGuest;
    UserQueries ub;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ub = new UserQueries();
        String entrySql = "SELECT * FROM room";
        fillRooms(entrySql);

    }
    public void fillRooms(String sql) {
        listViewGuest.getItems().clear();



        try {
            PreparedStatement preparedStatement = ub.connection.prepareStatement(sql);


            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Main.roomobject = new Room(rs.getString("roomCode"), rs.getString("roomType"),
                        rs.getString("roomPhone"), rs.getString("roomPrice"), rs.getString("roomStatus"));


                    listViewGuest.getItems().addAll(Main.roomobject.getRoomCode() +
                            " - " +
                            Main.roomobject.getRoomType() + "  -            " +
                            Main.roomobject.getRoomPhone() +
                            "   -             " + Main.roomobject.getRoomPrice() +
                            "-                 " + Main.roomobject.getRoomStatus() + "  -                  ");


                }

        } catch (SQLException ex) {

        }
    }



}
