package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.clinics.Hall;
import com.psw.clinicalcentre.clinics.HallDTO;

public class HallConverter {

    public static HallDTO hallToHallDto(Hall hall){
        return HallDTO.builder().id(hall.getId()).name(hall.getName()).build();
    }
}
