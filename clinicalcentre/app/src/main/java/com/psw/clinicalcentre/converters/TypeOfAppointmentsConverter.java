package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.types.TypesOfAppointments;
import com.psw.clinicalcentre.types.TypesOfAppointmentsDTO;

public class TypeOfAppointmentsConverter {

    public static TypesOfAppointmentsDTO typeToTypeDto(TypesOfAppointments type){
        return TypesOfAppointmentsDTO.builder().id(type.getId()).name(type.getName())
                .clinicId(type.getClinic() == null ? null : type.getClinic().getId()).build();
    }

}
