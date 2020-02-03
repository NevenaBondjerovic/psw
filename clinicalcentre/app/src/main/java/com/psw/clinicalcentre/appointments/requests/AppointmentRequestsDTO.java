package com.psw.clinicalcentre.appointments.requests;

import com.psw.clinicalcentre.appointments.AppointmentDTO;
import com.psw.clinicalcentre.users.BasicUserDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestsDTO {

    private Integer id;
    private AppointmentDTO appointment;
    private Integer adminId;
    private BasicUserDetailsDTO user;

}
