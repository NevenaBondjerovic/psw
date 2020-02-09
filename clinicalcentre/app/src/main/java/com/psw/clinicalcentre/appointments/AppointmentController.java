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

    @GetMapping("/doctor/{id}")
    public ResponseEntity<Set<AppointmentDTO>> findAllFutureScheduledAppointmentsForDoctorId(@PathVariable("id") Integer id){
        return new ResponseEntity<>(
                appointmentService.findAllFutureForDoctor(id).stream().map(AppointmentConverter::appointmentToAppointmentDTO)
                        .collect(Collectors.toSet()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(
                AppointmentConverter.appointmentToAppointmentDTO(appointmentService.findById(id)),
                HttpStatus.OK);
    }

    @PostMapping("/doctors")
    public ResponseEntity<Set<SearchDoctorsResponse>> findByClinicAndDateAndType(@RequestBody SearchDoctorsRequest request){
        if(request.getDate() == null && request.getType() == null){
            return new ResponseEntity<>(
                    appointmentService.findByClinicId(request.getClinicId()),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                appointmentService.findByClinicAndDateAndType(request.getClinicId(), request.getDate(), request.getType()),
                HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Set<AppointmentWithScoreDTO>> findAllPastAppointmentsForUser(@PathVariable("id") Integer id){
        return new ResponseEntity<>(appointmentService.findAllPastAppointmentsForUser(id), HttpStatus.OK);
    }

}
