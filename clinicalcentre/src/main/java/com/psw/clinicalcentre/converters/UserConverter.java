package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.users.UserDTO;
import com.psw.clinicalcentre.users.User;

public class UserConverter {

    public static UserDTO userToUserDto(User user){
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getAddress(),
                user.getCity(), user.getState(), user.getPhoneNumber(), user.getInsuranceNumber());
    }

    public static User userDtoToUser(UserDTO user, Boolean activated){
        return new User(user.getId(), user.getUsername(), user.getPassword(), user.getName(), user.getSurname(),
                user.getAddress(), user.getCity(), user.getState(), user.getPhoneNumber(), user.getInsuranceNumber(), activated);
    }

}
