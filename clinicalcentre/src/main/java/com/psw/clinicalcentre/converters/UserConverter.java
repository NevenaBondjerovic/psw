package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.users.RegistrationRequest;
import com.psw.clinicalcentre.users.User;

public class UserConverter {

    public static User registrationRequestToUser(RegistrationRequest request){
        return new User(null, request.getUsername(), request.getPassword(), request.getName(), request.getSurname(),
                request.getAddress(), request.getCity(), request.getState(), request.getPhoneNumber(),
                request.getInsuranceNumber(), request.getActivated());
    }

}
