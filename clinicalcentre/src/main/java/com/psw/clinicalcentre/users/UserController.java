package com.psw.clinicalcentre.users;

import com.psw.clinicalcentre.converters.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void loginUser(@RequestBody LoginRequest loginRequest){
        userService.findUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void registerUser(@RequestBody @Valid RegistrationRequest request){
        userService.registerUser(UserConverter.registrationRequestToUser(request));
    }

}
