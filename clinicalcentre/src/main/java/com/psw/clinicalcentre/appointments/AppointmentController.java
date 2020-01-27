package com.psw.clinicalcentre.appointments;

import com.psw.clinicalcentre.converters.AppointmentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(
                AppointmentConverter.appointmentToAppointmentDTO(appointmentService.findById(id)),
                HttpStatus.OK);
    }

}
