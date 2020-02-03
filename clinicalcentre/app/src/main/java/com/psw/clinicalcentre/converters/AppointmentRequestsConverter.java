package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.appointments.requests.AppointmentRequests;
import com.psw.clinicalcentre.appointments.requests.AppointmentRequestsDTO;

public class AppointmentRequestsConverter {

    public static AppointmentRequestsDTO requestToRequestDto(AppointmentRequests request){
        return AppointmentRequestsDTO.builder().id(request.getId())
                .appointment(AppointmentConverter.appointmentToAppointmentDTO(request.getAppointment()))
                .adminId(request.getAdmin().getId())
                .user(UserConverter.userToBasicUserDetailsDto(request.getUser())).build();
    }

}
