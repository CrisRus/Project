package Controllers;

import DatabaseConnection.UserQueries;
import Model.Room;
import Model.SceneSwitcher;
//import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GuestInfoController implements Initializable {
    private SceneSwitcher sceneSwitcher;
    public String realCode;


    String status;
    @FXML
    public ListView<String> listViewGuest;
    UserQueries ub;
    @FXML
    TextField roomCode;
    @FXML
    TextField firstName,lastName,Email,Address,Phone,Service,leaveHotel;
    @FXML
    DatePicker checkIn,checkOut;
    @FXML


    public String roomtype;
    @FXML
    Button reserveButton;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ub = new UserQueries();
        String entrySql = "SELECT * FROM room";
        fillRooms(entrySql);
        reserveButton.setDisable(true);
        sceneSwitcher = SceneSwitcher.getInstance();



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

    @FXML
    public void saveButton(ActionEvent ae) {
        if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || Email.getText().isEmpty() || Address.getText().isEmpty() ||
                Phone.getText().isEmpty() || roomCode.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Some fields are empty");
            alert.setContentText("Please fill in all fields and try again");
            alert.show();

        }

        else {
            try {
                String query="SELECT * FROM room WHERE roomCode ='" + roomCode.getText().toString().trim() + "'";
                PreparedStatement preparedStatement = ub.connection.prepareStatement(query);


                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {

                    status = rs.getString("roomstatus");
                    realCode=rs.getString("roomcode");
                    if (status == "unavailable" ){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("The room is currently occupied");
                        alert.setContentText("please select another vacant room");
                        alert.show();
                        reserveButton.setDisable(true);
                        roomtype = rs.getString("roomtype");




                    } else {
                        roomtype = rs.getString("roomtype");
                        reserveButton.setDisable(false);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Succes");
                        alert.setContentText("RoomType Collected FromDatabase,now hit the save button to reserve the room");
                        alert.show();
                        reserveButton.setDisable(false);

                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalidroomcode");
                alert.setContentText("please enter a valid roomcode");
                alert.show();


            }

        }

    }
    @FXML
    public void makeReservation(ActionEvent ae) throws Exception {




        int result = 0;
        String sql = "INSERT INTO cusBooking (firstName,lastName,email,address,phone,roomType,roomCode,startDate,endDate,services) VALUES (?,?,?,?,?,?,?,?,?,?)";




        PreparedStatement ps = ub.connection.prepareStatement(sql);
        ps.setString(1, firstName.getText().toString());
        ps.setString(2, lastName.getText().toString());
        ps.setString(3, Email.getText().toString());
        ps.setString(4, Address.getText().toString());
        ps.setString(5, Phone.getText().toString());
        ps.setString(6, roomtype);
        ps.setString(7, roomCode.getText().toString());
        ps.setString(8, checkIn.getValue().toString());
        ps.setString(9, checkOut.getValue().toString());
        ps.setString(10, Service.getText().toString());

        result = ps.executeUpdate();


        if (result > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Booking Successful");
            alert.setContentText("Booking added succesfully");
            alert.show();
            updateRoomStatus();
            listViewGuest.getItems().clear();

            ArrayList<String> booking=new ArrayList<String>();
            booking.add(firstName.getText().toString());
            booking.add(lastName.getText().toString());
            booking.add(Email.getText().toString());
            booking.add(Address.getText().toString());
            booking.add(Phone.getText().toString());
            booking.add(roomtype);
            booking.add(roomCode.getText().toString());
            booking.add(checkIn.getValue().toString());
            booking.add(checkOut.getValue().toString());
            booking.add(Service.getText().toString());
            listViewGuest.getItems().addAll(booking.get(0) +
                    " - " +booking.get(1) + " - "+ " - " +booking.get(2) + " - "+ " - " +booking.get(3) + " - "+ " - " +booking.get(4) + " - "+ " - " +booking.get(5) +
                    " - " +booking.get(6) + " - " + " - " +booking.get(7) + " - "+ " - " +booking.get(8) + " - "
                    +  " - " +booking.get(9));
            clearFields();


        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Booking unsuccessful");
            alert.setContentText("Booking is not added succesfully");
            alert.show();

        }
    }
    private void updateRoomStatus() {
        String text = roomCode.getText().toString().trim();
        String sql = "UPDATE room SET roomStatus=? WHERE roomCode=?";

        try {
            PreparedStatement ps = (PreparedStatement) ub.connection.prepareStatement(sql);
            ps.setString(1, "Unavailable");
            ps.setString(2, text);

            ps.executeUpdate();


        } catch (SQLException ex) {

        }
    }
    @FXML
    private void checkOut() {
        if (leaveHotel.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You must enter a valid roomcode before you can check out");
            alert.show();


        } else {

            String text = leaveHotel.getText().toString().trim();
            String sql = "UPDATE room SET roomStatus=? WHERE roomCode=?";

            try {
                PreparedStatement ps = (PreparedStatement) ub.connection.prepareStatement(sql);
                ps.setString(1, "available");
                ps.setString(2, text);

                ps.executeUpdate();
                fillRooms("SELECT * FROM room");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("iNFORMATION");
                alert.setContentText("YOU HAVE SUCCESFULLY CHECKED OUT");
                alert.show();


            } catch (SQLException ex) {

            }
        }
    }
    public void clearFields(){
        firstName.setText("");

        lastName.setText("");
        Email.setText("");
        Address.setText("");
        Phone.setText("");
        roomCode.setText("");
        Service.setText("");





    }
    @FXML
    public void Back(ActionEvent ae) throws IOException {
        sceneSwitcher.changeScene(ae, "../View/login.fxml");
    }


}









