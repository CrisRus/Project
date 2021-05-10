package Model;

public class Admin {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String hotelId;
    private String employeeId;

    public Admin(String email, String password, String firstname, String lastname, String phoneNumber, String hotelId, String employeeId) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.hotelId = hotelId;
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getEmployeeId() {
        return employeeId;

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
