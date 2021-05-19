package Controllers;

import DatabaseConnection.UserQueries;
import Model.Room;


import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;
import java.io.IOException;
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
     javafx.scene.control.TextField search;


    @FXML
    public ListView<String> listView;
    @FXML
    private StackPane stackpane;


    public void initialize(URL location, ResourceBundle resources) {
        ub = new UserQueries();
        String entrysql = "SELECT * FROM room";
        fillrooms(entrysql);


    }

    public void fillrooms(String sql) {
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

    @FXML
    public void back(javafx.event.ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void close(ActionEvent event) throws IOException {
      /*  DialogLayout dialogLayout = new DialogLayout();
        dialogLayout.setHeading(new Text("Close"));
        dialogLayout.setBody(new Text("Do You want to exit !"));

        Button ok = new JFXButton("Ok");
        Button Cancel = new JFXButton("Cancel");

        Dialog dialog = new Dialog(stackpane, dialogLayout, Dialog.DialogTransition.CENTER);

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                System.exit(0);
            }
        });

        Cancel.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });

        dialogLayout.setActions(ok, Cancel);
        dialog.show();
    }

       */
    }

    @FXML
    private void searchbyroomcode(ActionEvent ae) {
        if (search.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("please enter the room code");
            alert.setContentText("enter the correct room code and search");
            alert.show();
        } else {

            try {
                fillrooms("SELECT * FROM room WHERE roomcode ='" + search.getText().toString().trim() + "'");
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("please enter a valid room code");
                alert.setContentText("enter the correct room code and search");
                alert.show();
            }
        }


    }
    @FXML
    private void makeunAvailable(ActionEvent ae) {

        String text=search.getText().toString().trim();
        int res=0;
        String sql="UPDATE room SET roomstatus=? WHERE roomcode=?";
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
            fillrooms("SELECT * FROM `room` WHERE 1");
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data update");
            alert.setHeaderText("Information Dialog");
            alert.setContentText("Error!");
            alert.showAndWait();
        }
    }
    @FXML
    private void searchavailable(ActionEvent event) {

        fillrooms("SELECT * FROM room WHERE roomStatus = 'available' ");
    }
    @FXML
    private void searchunavailable(ActionEvent event) {

        fillrooms("SELECT * FROM room WHERE roomStatus = 'unavailable' ");
    }


}















