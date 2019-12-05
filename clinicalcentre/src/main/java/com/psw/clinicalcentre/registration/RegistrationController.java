package com.psw.clinicalcentre.registration;

import com.psw.clinicalcentre.converters.RegistrationConverter;
import com.psw.clinicalcentre.converters.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/registration")
@CrossOrigin(value = "*")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void registerUser(@RequestBody @Valid RequestForRegistration request){
        request.setActivated(Boolean.FALSE);
        registrationService.registerUser(RegistrationConverter.requestForRegistrationToUser(request));
    }

    @GetMapping
    public ResponseEntity<ResponseForUnprocessedRequests> findUnprocessedRequests(){
        return new ResponseEntity<ResponseForUnprocessedRequests>(new ResponseForUnprocessedRequests(
                registrationService.findUnprocessedRequests().stream()
                        .map(UserConverter::userToUserResponse).collect(Collectors.toSet())), HttpStatus.OK);
    }

}
