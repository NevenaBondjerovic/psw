package com.psw.clinicalcentre.registration;

import com.psw.clinicalcentre.users.User;

import java.util.Set;

public interface RegistrationService {

    void registerUser(User user);

    Set<User> findUnprocessedRequests();

    void acceptRegistrationRequest(AcceptRejectRequest request);

    void rejectRegistrationRequest(AcceptRejectRequest request);

}
