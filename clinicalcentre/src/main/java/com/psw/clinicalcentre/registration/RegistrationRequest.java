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

    @Column(name = "processed")
    private Boolean requestsProcessedByAdministrator;

    private Boolean approved;
    private String declineReason;

}
