package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.registration.RequestForRegistration;
import com.psw.clinicalcentre.users.User;

public class UserConverter {

    public static User requestForRegistrationToUser(RequestForRegistration request){
        return new User(null, request.getUsername(), request.getPassword(), request.getName(), request.getSurname(),
                request.getAddress(), request.getCity(), request.getState(), request.getPhoneNumber(),
                request.getInsuranceNumber(), request.getActivated());
    }

}
