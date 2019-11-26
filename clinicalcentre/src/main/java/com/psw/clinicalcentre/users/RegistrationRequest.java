package com.psw.clinicalcentre.users;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegistrationRequest {

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
    private Boolean approvedByAdministrator;

    @NotNull
    private Boolean activated;

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

    public Boolean getApprovedByAdministrator() {
        return approvedByAdministrator;
    }

    public Boolean getActivated() {
        return activated;
    }
}
