package com.psw.clinicalcentre.users;

import javax.validation.constraints.NotBlank;

public class LoginDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
