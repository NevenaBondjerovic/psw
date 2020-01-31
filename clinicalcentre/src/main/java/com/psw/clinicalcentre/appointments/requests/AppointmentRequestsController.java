package com.psw.clinicalcentre.appointments.requests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/appointmentrequests")
@CrossOrigin(value = "*")
public class AppointmentRequestsController {

    @Autowired
    private AppointmentRequestsService appointmentRequestsService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody @Valid SaveRequestDTO request){
        appointmentRequestsService.save(request.getAppointmentId(), request.getUserId());
    }

}
