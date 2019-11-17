package com.psw.clinicalcentre.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public HttpStatus loginUser(@RequestBody LoginRequest loginRequest){
        return userService.findUser(loginRequest.getUsername(), loginRequest.getPassword()) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }

}
