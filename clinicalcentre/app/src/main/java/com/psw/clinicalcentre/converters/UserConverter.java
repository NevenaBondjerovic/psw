package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.users.BasicUserDetailsDTO;
import com.psw.clinicalcentre.users.UserDTO;
import com.psw.clinicalcentre.users.User;
import com.psw.clinicalcentre.users.UserType;

public class UserConverter {

    public static UserDTO userToUserDto(User user){
        return UserDTO.builder().id(user.getId()).username(user.getUsername()).password(user.getPassword())
                .name(user.getName()).surname(user.getSurname()).address(user.getAddress())
                .city(user.getCity()).state(user.getState()).phoneNumber(user.getPhoneNumber())
                .insuranceNumber(user.getInsuranceNumber()).activated(user.getActivated())
                .userType(user.getUserType() != null ? user.getUserType() : UserType.NEW_USER).build();
    }

    public static BasicUserDetailsDTO userToBasicUserDetailsDto(User user){
        return BasicUserDetailsDTO.builder().id(user.getId())
                .name(user.getName()).surname(user.getSurname()).build();
    }

    public static User userDtoToUser(UserDTO user, Boolean activated){
        return User.builder().id(user.getId()).username(user.getUsername()).password(user.getPassword())
                .name(user.getName()).surname(user.getSurname()).address(user.getAddress())
                .city(user.getCity()).state(user.getState()).phoneNumber(user.getPhoneNumber())
                .insuranceNumber(user.getInsuranceNumber()).activated(activated)
                .userType(user.getUserType() != null ? user.getUserType() : UserType.NEW_USER).build();
    }

}
