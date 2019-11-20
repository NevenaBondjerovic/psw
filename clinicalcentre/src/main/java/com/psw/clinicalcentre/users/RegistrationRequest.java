package com.psw.clinicalcentre.users;

public class RegistrationRequest {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String state;
    private String phoneNumber;
    private String insuranceNumber;
    private Boolean approvedByAdministrator;
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
