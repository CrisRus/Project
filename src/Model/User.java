package Model;

public class User {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phonenumber;



    public User(String email,String firstname, String password,  String lastname, String phonenumber) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber=phonenumber;

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


    public String getPhonenumber() {
        return phonenumber;
    }



    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                 +
                '}';
    }
}




