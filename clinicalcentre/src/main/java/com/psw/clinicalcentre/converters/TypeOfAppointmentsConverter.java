package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.appointments.TypesOfAppointments;
import com.psw.clinicalcentre.appointments.TypesOfAppointmentsDTO;

public class TypeOfAppointmentsConverter {

    public static TypesOfAppointmentsDTO typeToTypeDto(TypesOfAppointments type){
        return TypesOfAppointmentsDTO.builder().id(type.getId()).name(type.getName()).build();
    }

}
