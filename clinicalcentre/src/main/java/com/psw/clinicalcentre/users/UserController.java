package com.psw.clinicalcentre.users;

import com.psw.clinicalcentre.registration.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.psw.clinicalcentre.converters.UserConverter.userToUserResponse;

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

    @GetMapping("/myprofile/{username}")
    public ResponseEntity<UserResponse> findUserByUsername(@PathVariable("username") @Valid String username){
        return new ResponseEntity<UserResponse>(userToUserResponse(userService.findByUsername(username)),
                HttpStatus.OK);
    }

    @PostMapping("/activate")
    @ResponseStatus(HttpStatus.OK)
    public void activateAccount(@RequestBody @Valid ActivationDataRequest request){
        userService.activateAccount(request.getId(), request.getUsername(), request.getPassword());
    }
}
