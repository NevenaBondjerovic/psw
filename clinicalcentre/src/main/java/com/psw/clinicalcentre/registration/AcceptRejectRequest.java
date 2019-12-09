package com.psw.clinicalcentre.registration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AcceptRejectRequest {

    @NotBlank
    private String username;

    @NotNull
    private Boolean approved;
    private String declineReason;

    public AcceptRejectRequest() {
    }

    public String getUsername() {
        return username;
    }

    public Boolean getApproved() {
        return approved;
    }

    public String getDeclineReason() {
        return declineReason;
    }
}
