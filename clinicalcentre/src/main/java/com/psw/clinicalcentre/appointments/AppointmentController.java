package com.psw.clinicalcentre.appointments;

import com.psw.clinicalcentre.converters.AppointmentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(value = "*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<Set<AppointmentDTO>> findAllAvailable(){
        return new ResponseEntity<>(
                appointmentService.findAllAvailable().stream().map(AppointmentConverter::appointmentToAppointmentDTO)
                .collect(Collectors.toSet()),
                HttpStatus.OK);
    }

}
