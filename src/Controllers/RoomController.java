package Controllers;

import DatabaseConnection.UserQueries;
import Model.Room;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomController implements Initializable {
    UserQueries ub;


    @FXML
    public ListView<String> listView;


    public void initialize(URL location, ResourceBundle resources) {
        ub = new UserQueries();
        fillrooms();


    }

    public void fillrooms() {

        String sql = "SELECT * FROM room";
        listView.getItems().clear();


        try {
            PreparedStatement preparedStatement = ub.connection.prepareStatement(sql);


            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Main.roomobject = new Room(rs.getString("roomcode"), rs.getString("roomtype"),
                        rs.getString("roomphone"), rs.getString("roomprice"), rs.getString("roomstatus"));
                listView.getItems().addAll(Main.roomobject.getRoomcode() +
                        "                                -             " +
                        Main.roomobject.getRoomtype() + "                                     -  " +
                        Main.roomobject.getRoomphone() +
                        "                         -  " + Main.roomobject.getRoomprice() +
                        "                         -  " + Main.roomobject.getRoomstatus() + "                                   -  ");


            }
        } catch (SQLException ex) {

        }
    }
}















