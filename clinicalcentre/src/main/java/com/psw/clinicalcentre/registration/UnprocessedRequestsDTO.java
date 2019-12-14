package com.psw.clinicalcentre.registration;

import com.psw.clinicalcentre.users.UserDTO;

import java.util.Set;

public class UnprocessedRequestsDTO {

    private Set<UserDTO> userData;

    public UnprocessedRequestsDTO(Set<UserDTO> userData) {
        this.userData = userData;
    }

    public Set<UserDTO> getUserData() {
        return userData;
    }

    public void setUserData(Set<UserDTO> userData) {
        this.userData = userData;
    }
}
