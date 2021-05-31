package Model;

import javafx.beans.property.StringProperty;

import java.sql.Date;

public class Guest {
    private String firstName;
    private String lastName;

    private String email;
    private String address;
    private String phone;


    private String roomType;
    private String roomCode;
    private String startDate;
    private String endDate;
    private String  services;

    public Guest(String firstName, String lastName, String email, String address, String phone, String roomType, String roomCode,
                 String startDate, String endDate, String services) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone=phone;
        this.roomType=roomType;
        this.roomCode=roomCode;
        this.startDate=startDate;
        this.endDate=endDate;
        this.services=services;
    }


    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getRoomType() {
        return roomType;
    }
    public String getRoomCode() {
        return roomCode;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public String getServices() {
        return services;
    }



}
