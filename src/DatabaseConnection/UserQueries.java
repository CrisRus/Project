package DatabaseConnection;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

public class UserQueries extends DatabaseConnection {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    

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
                    if (resultSet.getString("email").equals(userIn) && resultSet.getString("password").equals(pwIn)) {
                        System.out.println(resultSet.getString("email") + " is email");
                        System.out.println(resultSet.getString("password") + " is password");
                        System.out.println("testing");
                        isVerified = true;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setContentText("You have successfully logged in.");
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
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return isVerified;
        }



    }



