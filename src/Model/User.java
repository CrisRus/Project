package Model;

public class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;



    public User(String email,String firstName, String password,  String lastName, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber=phoneNumber;

    }







    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public String getFirstname() {
        return firstName;
    }


    public String getLastname() {
        return lastName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }



    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                 +
                '}';
    }
}




