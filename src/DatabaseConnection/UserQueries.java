package DatabaseConnection;

import Controllers.Main;
import Controllers.RoomController;
import Model.Room;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserQueries extends DatabaseConnection {


    public Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    public String status = "";
    RoomController rc=new RoomController();



    public UserQueries() {
        // everytime we call an object of UserQueries a connection is made automatically
        try {
            this.connection = getConnection();
            this.statement = connection.createStatement();

            // this.userInformation = new ArrayList<>();
        } catch (SQLException sq) {
            System.out.println(sq.getMessage());
        }
    }

    public boolean verifyLogin(String userIn, String pwIn) {
        System.out.println("Entered verifylogin.");
        boolean isVerified = false;
        try {
            //connect();
            String sql = "SELECT email, password FROM user WHERE email = '" + userIn + "' AND password = '" + pwIn + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            System.out.println("userin: " + userIn + " , pwin: " + pwIn);
            while (resultSet.next()) {
                //step below helps to index the column
                if (resultSet.getString("email").equals("admin")
                        && resultSet.getString("password").equals("admin")) {
                    System.out.println(resultSet.getString("email") + " is email");
                    System.out.println(resultSet.getString("password") + " is password");
                    System.out.println("admin login");
                    isVerified = true;
                    status = "admin";
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("You have successfully logged inas an admin");
                    alert.show();
                } else if (resultSet.getString("email").equals(userIn) && resultSet.getString("password").equals(pwIn)) {
                    System.out.println(resultSet.getString("email") + " is email");
                    System.out.println(resultSet.getString("password") + " is password");
                    System.out.println("Employee login");
                    isVerified = true;
                    status = "employee";
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("You have successfully logged in as an Employee");
                    alert.show();
                }

            }

            if (isVerified == false) {
                System.out.println("Wrong email and/or password.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Wrong email and/or password.");
                alert.show();
            }
           // disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isVerified;
    }

    public void createUser(User user) {
        System.out.println("Entered createuser");

        try {

            String query = "INSERT INTO `user` (`email`, `firstname`, `password`, `lastname`, `phonenumber` ) VALUES " +
                    "('" + user.getEmail() + "', '" + user.getFirstname() + "', '" + user.getPassword() + "', '" + user.getLastname() + "', '" + user.getPhonenumber() + "');";
            System.out.println(query);
            statement = connection.createStatement();
            statement.executeUpdate(query);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("The User with firstname  " + "`" + user.getFirstname() + "`" + " and email   " + "`" + user.getEmail() + "`" + "has been added");
            alert.show();
            System.out.println("User created: ");
        } catch (NumberFormatException e) {

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("transaction failed");
            alert.show();


        }

    }
    public boolean verifyGuestLogin(String userIn, String pwIn) {

        boolean isVerified = false;
        try {

            String sql = "SELECT email, password FROM guest WHERE email = '" + userIn + "' AND password = '" + pwIn + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            System.out.println("userin: " + userIn + " , pwin: " + pwIn);
            while (resultSet.next()) {
                //step below helps to index the column

                if (resultSet.getString("email").equals(userIn) && resultSet.getString("password").equals(pwIn)) {
                    System.out.println(resultSet.getString("email") + " is email");
                    System.out.println(resultSet.getString("password") + " is password");
                    System.out.println("Employee login");
                    isVerified = true;
                    status = "guest";
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("You have successfully logged in as a guest");
                    alert.show();
                }

            }

            if (isVerified == false) {
                System.out.println("Wrong email and/or password.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Wrong email and/or password.");
                alert.show();
            }
           // disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isVerified;
    }




}











