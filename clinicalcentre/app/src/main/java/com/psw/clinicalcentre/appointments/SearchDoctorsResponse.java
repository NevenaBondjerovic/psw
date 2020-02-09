package com.psw.clinicalcentre.appointments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDoctorsResponse {

    private Integer doctorId;
    private String doctorName;
    private String doctorSurname;
    private Integer score;
    private Set<AppointmentDataDTO> appointmentData;

}
