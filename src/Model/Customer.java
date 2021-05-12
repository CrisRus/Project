package Model;

import java.sql.Date;

public class Customer {
    private String firstName;
    private String lastName;
    private Date dob;
    private int age;
    private String customerSSN;

    public Customer(String firstName, String lastName, Date dob, int age,String customerSSN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.age = age;
        this.customerSSN=customerSSN;
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

    public String getCustomerSSN() { return customerSSN; }
}
