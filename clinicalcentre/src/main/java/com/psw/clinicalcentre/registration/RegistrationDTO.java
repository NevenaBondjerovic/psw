package com.psw.clinicalcentre.registration;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegistrationDTO {

    @NotBlank
    @Email
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

    @NotNull
    private Boolean activated;

    public RegistrationDTO(@NotBlank @Email String username, @NotBlank String password, @NotBlank String name,
                           @NotBlank String surname, @NotBlank String address, @NotBlank String city,
                           @NotBlank String state, @NotBlank String phoneNumber, @NotBlank String insuranceNumber,
                           @NotNull Boolean activated) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.insuranceNumber = insuranceNumber;
        this.activated = activated;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }
}
