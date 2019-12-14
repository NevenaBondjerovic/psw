package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.registration.RegistrationDTO;
import com.psw.clinicalcentre.users.User;

public class RegistrationConverter {

    public static User registrationDtoToUser(RegistrationDTO request){
        return new User(null, request.getUsername(), request.getPassword(), request.getName(), request.getSurname(),
                request.getAddress(), request.getCity(), request.getState(), request.getPhoneNumber(),
                request.getInsuranceNumber(), request.getActivated());
    }



}
