package Model;

public class Room {
    private int bedNum;
    private String view;
    private int roomNum;
    private boolean isAvailable;

    public Room(int bedNum, String view, int roomNum, boolean isAvailable) {
        this.bedNum = bedNum;
        this.view = view;
        this.roomNum = roomNum;
        this.isAvailable = isAvailable;
    }

    public int getBedNum() {
        return bedNum;
    }

    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
