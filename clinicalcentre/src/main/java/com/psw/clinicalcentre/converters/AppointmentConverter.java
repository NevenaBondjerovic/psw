package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.appointments.Appointment;
import com.psw.clinicalcentre.appointments.AppointmentDTO;

public class AppointmentConverter {

    public static AppointmentDTO appointmentToAppointmentDTO(Appointment appointment) {
        return AppointmentDTO.builder().id(appointment.getId()).dateOfAppointment(appointment.getDateOfAppointment())
                .timeOfAppointment(appointment.getTimeOfAppointment())
                .clinic(ClinicConverter.clinicToClinicDto(appointment.getClinic()))
                .hall(HallConverter.hallToHallDto(appointment.getHall()))
                .doctor(UserConverter.userToBasicUserDetailsDto(appointment.getDoctor()))
                .type(TypeOfAppointmentsConverter.typeToTypeDto(appointment.getType()))
                .pricelist(PricelistConverter.pricelistToPricelistDto(appointment.getPricelist()))
                .scheduledFor(appointment.getScheduledFor() == null ? null
                        : UserConverter.userToBasicUserDetailsDto(appointment.getScheduledFor()))
                .build();
    }

}
