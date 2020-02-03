package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.clinics.Clinic;
import com.psw.clinicalcentre.clinics.ClinicDTO;

public class ClinicConverter {

    public static ClinicDTO clinicToClinicDto(Clinic clinic){
        return ClinicDTO.builder().id(clinic.getId()).name(clinic.getName())
                .address(clinic.getAddress()).score(clinic.getScore()).build();
    }

}
