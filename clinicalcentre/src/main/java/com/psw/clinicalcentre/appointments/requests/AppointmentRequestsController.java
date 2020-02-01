package com.psw.clinicalcentre.appointments.requests;

import com.psw.clinicalcentre.converters.AppointmentRequestsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

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

    @GetMapping("/{adminId}")
    public ResponseEntity<Set<AppointmentRequestsDTO>> findAllUnprocessedForAdminId(@PathVariable("adminId") Integer adminId) {
        return new ResponseEntity<>(appointmentRequestsService.findAllUnprocessedByAdmin(adminId)
                .stream().map(AppointmentRequestsConverter::requestToRequestDto)
                .collect(Collectors.toSet()), HttpStatus.OK);
    }

}
