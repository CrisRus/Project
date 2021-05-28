package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;


public class Room  {
    public String roomcode;
    public String roomtype;

    public String roomphone;
    public String roomprice;
    public String roomstatus;





    public Room(String roomcode, String roomType, String roomPhone, String roomPrice, String roomStatus) {
        this.roomcode = roomcode;
        this.roomtype = roomType;
        this.roomphone = roomPhone;
        this.roomprice = roomPrice;
        this.roomstatus = roomStatus;
    }
    public String getRoomcode(){
    return roomcode;
    }
    public String getRoomtype(){
        return roomtype;
    }
    public String getRoomphone(){
        return roomphone;
    }
    public String getRoomprice(){
        return roomprice;
    }
    public String getRoomstatus(){
        return roomstatus;
    }
}










