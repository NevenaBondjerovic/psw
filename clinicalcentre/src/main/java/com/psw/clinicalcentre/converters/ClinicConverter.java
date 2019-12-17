package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.clinics.Clinic;
import com.psw.clinicalcentre.clinics.ClinicDTO;

public class ClinicConverter {

    public static ClinicDTO clinicToClinicDto(Clinic clinic){
        return new ClinicDTO(clinic.getId(), clinic.getName(), clinic.getAddress(), clinic.getScore());
    }

}
