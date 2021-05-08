package Model;

import java.sql.Date;

public class Customer {
    private String firstName;
    private String lastName;
    private Date dob;
    private int age;

    public Customer(String firstName, String lastName, Date dob, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.age = age;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
