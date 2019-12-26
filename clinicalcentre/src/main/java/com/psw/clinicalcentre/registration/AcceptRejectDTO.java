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

    public AcceptRejectDTO(@NotBlank String username, @NotNull Boolean approved, String declineReason) {
        this.username = username;
        this.approved = approved;
        this.declineReason = declineReason;
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

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
