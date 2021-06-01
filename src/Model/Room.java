package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;


public class Room  {
    public String roomCode;
    public String roomType;

    public String roomPhone;
    public String roomPrice;
    public String roomStatus;

    protected enum Status { AVAILABLE, BOOKED, OCCUPIED };
    private  Status status;



    public Room(String roomCode, String roomType, String roomPhone, String roomPrice, String roomStatus) {
        this.roomCode = roomCode;
        this.roomType = roomType;
        this.roomPhone = roomPhone;
        this.roomPhone = roomPrice;
        this.roomStatus = roomStatus;
    }
    public String getRoomCode(){
    return roomCode;
    }
    public String getRoomType(){
        return roomType;
    }
    public String getRoomPhone(){
        return roomPhone;
    }
    public String getRoomPrice(){
        return roomPrice;
    }
    public String getRoomStatus(){
        return roomStatus;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomCode='" + roomCode + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomPhone='" + roomPhone + '\'' +
                ", roomPrice='" + roomPrice + '\'' +
                ", roomStatus='" + roomStatus + '\'' +
                ", status=" + status +
                '}';
    }
}










