package com.psw.clinicalcentre.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String insuranceNumber;

    public UserDTO(Integer id, String username, String password, String name, String surname, String address, String city,
                   String state, String phoneNumber, String insuranceNumber) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }
}