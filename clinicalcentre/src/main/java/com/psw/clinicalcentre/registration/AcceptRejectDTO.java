package com.psw.clinicalcentre.registration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AcceptRejectDTO {

    @NotBlank
    private String username;

    @NotNull
    private Boolean approved;
    private String declineReason;

    public AcceptRejectDTO() {
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
