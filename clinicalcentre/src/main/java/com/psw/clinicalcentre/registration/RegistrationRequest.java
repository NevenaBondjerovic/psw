package com.psw.clinicalcentre.registration;

import com.psw.clinicalcentre.users.User;

import javax.persistence.*;

@Entity
@Table(name = "REGISTRATION_REQUESTS")
public class RegistrationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private User user;

    private Boolean processed;
    private Boolean approved;
    private String declineReason;

    public RegistrationRequest() {
    }

    public RegistrationRequest(User user, Boolean processed, Boolean approved, String declineReason) {
        this.user = user;
        this.processed = processed;
        this.approved = approved;
        this.declineReason = declineReason;
    }

    public User getUser() {
        return user;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public Boolean getApproved() {
        return approved;
    }

}
