package Model;

import java.util.Date;

public class Booking {

    private int bookId;
    private int roomNumber;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalPrice;
    private String customerSSN;

    public Booking() {
    }

    public Booking(int bookId, int roomNumber, Date checkInDate, Date checkOutDate, double totalPrice, String customerSSN) {
        this.bookId = bookId;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.customerSSN = customerSSN;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public Date getCheckInDate() {
        return checkInDate;
    }


}