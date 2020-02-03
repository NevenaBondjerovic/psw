package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.registration.RegistrationDTO;
import com.psw.clinicalcentre.users.User;

public class RegistrationConverter {

    public static User registrationDtoToUser(RegistrationDTO request){
        return User.builder().id(null).username(request.getUsername()).password(request.getPassword())
                .name(request.getName()).surname(request.getSurname()).address(request.getAddress())
                .city(request.getCity()).state(request.getState()).phoneNumber(request.getPhoneNumber())
                .insuranceNumber(request.getInsuranceNumber()).activated(request.getActivated()).build();
    }



}
