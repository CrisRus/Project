package DatabaseConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public abstract class DatabaseConnection {
   static  java.sql.Connection connection=null;
  static  Statement statement=null;
    ResultSet resultSet=null;
    private String url1 = "jdbc:mysql://den1.mysql2.gear.host:3306/group10bookstore?user=dump20210506&password=Jl3C2!!H9QMU&serverTimezone=UTC&useSSL=false";
    String url="jdbc:mysql://den1.mysql2.gear.host:3306/dump20210506";


    protected DatabaseConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           // String url = "jdbc:mysql://localhost/hotelmanagementprojectcourse";
            connection=DriverManager.getConnection(url,"dump20210506","Jl3C2!!H9QMU");
            statement = connection.createStatement();
            System.out.println("Connected");

        } catch (Exception e) {
        }


    }
    public java.sql.Connection getConnection() {
        return connection;
    }
    // disconnect from database
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

}


