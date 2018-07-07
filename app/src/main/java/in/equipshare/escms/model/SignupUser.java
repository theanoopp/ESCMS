package in.equipshare.escms.model;

import java.io.Serializable;

public class SignupUser implements Serializable {

    private String companyName;
    private String companyType;
    private String email;
    private String contactNumber;
    private String contactPerson;
    private String password;
    private String state;
    private String city;
    private String pin;
    private String address;

    private String ownerName;
    private String ownerEmail;


    public SignupUser() {

    }


    public SignupUser(String companyName, String companyType, String email, String contactNumber, String contactPerson, String password, String state, String city, String pin, String address, String ownerName, String ownerEmail) {
        this.companyName = companyName;
        this.companyType = companyType;
        this.email = email;
        this.contactNumber = contactNumber;
        this.contactPerson = contactPerson;
        this.password = password;
        this.state = state;
        this.city = city;
        this.pin = pin;
        this.address = address;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
