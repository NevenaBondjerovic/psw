package com.psw.clinicalcentre.clinics;

import com.psw.clinicalcentre.converters.ClinicConverter;
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
@RequestMapping("/clinics")
@CrossOrigin(value = "*")
public class ClinicController {

    @Autowired
    private ClinicsService clinicsService;

    @GetMapping
    public ResponseEntity<Set<ClinicDTO>> findAll(){
        return new ResponseEntity<Set<ClinicDTO>>(
                clinicsService.findAll().stream().map(ClinicConverter::clinicToClinicDto).collect(Collectors.toSet()),
                HttpStatus.OK);
    }

}
