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
    public void registerUser(@RequestBody @Valid RegistrationDTO request){
        request.setActivated(Boolean.FALSE);
        registrationService.registerUser(RegistrationConverter.registrationDtoToUser(request));
    }

    @GetMapping
    public ResponseEntity<UnprocessedRequestsDTO> findUnprocessedRequests(){
        return new ResponseEntity<UnprocessedRequestsDTO>(new UnprocessedRequestsDTO(
                registrationService.findUnprocessedRequests().stream()
                        .map(UserConverter::userToUserDto).collect(Collectors.toSet())), HttpStatus.OK);
    }

    @PostMapping("/requests")
    @ResponseStatus(HttpStatus.OK)
    public void acceptRegistrationRequest(@RequestBody @Valid AcceptRejectDTO request){
        if (request.getApproved())
            registrationService.acceptRegistrationRequest(request);
        else
            registrationService.rejectRegistrationRequest(request);

    }


}
