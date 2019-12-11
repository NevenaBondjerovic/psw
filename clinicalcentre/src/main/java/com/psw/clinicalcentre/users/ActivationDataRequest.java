package com.psw.clinicalcentre.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ActivationDataRequest {

    @NotNull
    private Integer id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public ActivationDataRequest() {
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
