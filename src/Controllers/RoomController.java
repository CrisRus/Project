package Controllers;

import DatabaseConnection.UserQueries;
import Model.Room;


import Model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RoomController implements Initializable {
    UserQueries ub;
    @FXML
     javafx.scene.control.TextField search;
    private SceneSwitcher sceneSwitcher;


    @FXML
    public ListView<String> listView;
    @FXML
    private StackPane stackpane;


    public void initialize(URL location, ResourceBundle resources) {
        ub = new UserQueries();
        String entrySql = "SELECT * FROM room";
        fillRooms(entrySql);
        sceneSwitcher = SceneSwitcher.getInstance();

    }

    public void fillRooms(String sql) {
        listView.getItems().clear();



        try {
            PreparedStatement preparedStatement = ub.connection.prepareStatement(sql);


            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Main.roomobject = new Room(rs.getString("roomCode"), rs.getString("roomType"),
                        rs.getString("roomPhone"), rs.getString("roomPrice"), rs.getString("roomStatus"));
                listView.getItems().addAll(Main.roomobject.getRoomCode() +
                        "                                -             " +
                        Main.roomobject.getRoomType() + "                                     -  " +
                        Main.roomobject.getRoomPhone() +
                        "                         -  " + Main.roomobject.getRoomPrice() +
                        "                         -  " + Main.roomobject.getRoomStatus() + "                                   -  ");


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
    private void searchByRoomCode(ActionEvent ae) {
        if (search.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("please enter the room code");
            alert.setContentText("enter the correct room code and search");
            alert.show();
        } else {

            try {
                fillRooms("SELECT * FROM room WHERE roomCode ='" + search.getText().toString().trim() + "'");
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("please enter a valid room code");
                alert.setContentText("enter the correct room code and search");
                alert.show();
            }
        }


    }
    @FXML
    private void makeUnAvailable(ActionEvent ae) {

        String text=search.getText().toString().trim();
        int res=0;
        String sql="UPDATE room SET roomStatus=? WHERE roomCode=?";
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
            fillRooms("SELECT * FROM `room` WHERE 1");
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

        fillRooms("SELECT * FROM room WHERE roomStatus = 'available' ");
    }
    @FXML
    private void searchunavailable(ActionEvent event) {

        fillRooms("SELECT * FROM room WHERE roomStatus = 'unavailable' ");
    }


}















