package com.psw.clinicalcentre.registration;

public class UserResponse {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String state;
    private String phoneNumber;
    private String insuranceNumber;

    public UserResponse(String username, String password, String name, String surname, String address, String city,
                        String state, String phoneNumber, String insuranceNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.insuranceNumber = insuranceNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public String getPassword() {
        return password;
    }
}
