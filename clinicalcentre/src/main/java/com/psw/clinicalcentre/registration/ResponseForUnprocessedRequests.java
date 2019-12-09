package com.psw.clinicalcentre.registration;

import java.util.Set;

public class ResponseForUnprocessedRequests {

    private Set<UserResponse> userData;

    public ResponseForUnprocessedRequests(Set<UserResponse> userData) {
        this.userData = userData;
    }

    public Set<UserResponse> getUserData() {
        return userData;
    }

    public void setUserData(Set<UserResponse> userData) {
        this.userData = userData;
    }
}
