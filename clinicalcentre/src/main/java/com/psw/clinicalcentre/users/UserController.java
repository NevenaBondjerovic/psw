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
    @ResponseStatus(HttpStatus.OK)
    public void loginUser(@RequestBody LoginDTO loginRequest){
        userService.findUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void findUserById(@PathVariable("id") @Valid Integer id){
        userService.findById(id);
    }

    @GetMapping("/myprofile/{username}")
    public ResponseEntity<UserDTO> findUserByUsername(@PathVariable("username") @Valid String username){
        return new ResponseEntity<UserDTO>(userToUserDto(userService.findByUsername(username)),
                HttpStatus.OK);
    }

    @PostMapping("/activate")
    @ResponseStatus(HttpStatus.OK)
    public void activateAccount(@RequestBody @Valid ActivationDataDTO request){
        userService.activateAccount(request.getId(), request.getUsername(), request.getPassword());
    }

    @PutMapping
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO user){
        return new ResponseEntity<>(userToUserDto(userService.save(userDtoToUser(user, TRUE))), HttpStatus.OK);
    }
}
