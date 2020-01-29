package com.psw.clinicalcentre.types;

import com.psw.clinicalcentre.clinics.ClinicDTO;
import com.psw.clinicalcentre.converters.ClinicConverter;
import com.psw.clinicalcentre.converters.TypeOfAppointmentsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/types")
@CrossOrigin(value = "*")
public class TypesController {

    @Autowired
    private TypesService typesService;

    @GetMapping
    public ResponseEntity<Set<String>> findAll(){
        return new ResponseEntity<>(
                typesService.findAllDistinctNames(),
                HttpStatus.OK);
    }

}
