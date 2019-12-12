package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.registration.UserResponse;
import com.psw.clinicalcentre.users.User;

public class UserConverter {

    public static UserResponse userToUserResponse(User user){
        return new UserResponse(user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getAddress(),
                user.getCity(), user.getState(), user.getPhoneNumber(), user.getInsuranceNumber());
    }

}
