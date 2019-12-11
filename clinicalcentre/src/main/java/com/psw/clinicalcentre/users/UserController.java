package com.psw.clinicalcentre.users;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void findUserById(@PathVariable("id") @Valid Integer id){
        userService.findById(id);
    }
}
