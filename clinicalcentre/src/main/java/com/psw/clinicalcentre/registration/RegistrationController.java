package com.psw.clinicalcentre.registration;

import com.psw.clinicalcentre.converters.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        registrationService.registerUser(UserConverter.requestForRegistrationToUser(request));
    }

}
