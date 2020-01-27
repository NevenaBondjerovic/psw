package com.psw.clinicalcentre.clinics;

import com.psw.clinicalcentre.converters.ClinicConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(
                clinicsService.findAll().stream().map(ClinicConverter::clinicToClinicDto).collect(Collectors.toSet()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicDTO> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(ClinicConverter.clinicToClinicDto(clinicsService.findById(id)),
                HttpStatus.OK);
    }

}
