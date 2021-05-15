package Model;

import javafx.beans.property.StringProperty;

import java.sql.Date;

public class Customer {
    private String firstname;
    private String lastname;

    private String email;
    private String address;
    private String phone;


    private String roomtype;
    private String roomcode;
    private String startdate;
    private String enddate;
    private String  services;

    public Customer(String firstname, String lastname, String email,String address,String phone,String roomtype, String roomcode,
                    String startdate,String enddate, String services) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phone=phone;
        this.roomtype=roomtype;
        this.roomcode=roomcode;
        this.startdate=startdate;
        this.enddate=enddate;
        this.services=services;
    }


    public String getfirstname() {
        return firstname;
    }
    public String getlastname() {
        return lastname;
    }
    public String getemail() {
        return email;
    }
    public String getaddress() {
        return address;
    }
    public String getphone() {
        return phone;
    }
    public String getroomtype() {
        return roomtype;
    }
    public String getroomcode() {
        return roomcode;
    }
    public String getstartdate() {
        return startdate;
    }
    public String getenddate() {
        return enddate;
    }
    public String getservices() {
        return services;
    }


}
