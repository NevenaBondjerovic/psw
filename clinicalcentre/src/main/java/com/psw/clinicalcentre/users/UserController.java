package com.psw.clinicalcentre.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.psw.clinicalcentre.converters.UserConverter.userDtoToUser;
import static com.psw.clinicalcentre.converters.UserConverter.userToUserDto;
import static java.lang.Boolean.TRUE;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody @Valid LoginDTO loginRequest){
        return new ResponseEntity<>(
                userToUserDto(userService.findUser(loginRequest.getUsername(), loginRequest.getPassword())),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void findUserById(@PathVariable("id") @Valid Integer id){
        userService.findById(id);
    }

    @GetMapping("/myprofile/{username}")
    public ResponseEntity<UserDTO> findUserByUsername(@PathVariable("username") @Valid String username){
        return new ResponseEntity<>(userToUserDto(userService.findByUsername(username)),
                HttpStatus.OK);
    }

    @PostMapping("/activate")
    public ResponseEntity<UserDTO> activateAccount(@RequestBody @Valid ActivationDataDTO request){
        return new ResponseEntity<>(
                userToUserDto(userService.activateAccount(request.getId(), request.getUsername(), request.getPassword())),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO user){
        return new ResponseEntity<>(userToUserDto(userService.save(userDtoToUser(user, TRUE))), HttpStatus.OK);
    }
}
