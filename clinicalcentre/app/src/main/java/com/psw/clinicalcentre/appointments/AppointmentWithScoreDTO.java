package com.psw.clinicalcentre.appointments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentWithScoreDTO {

    @NotNull
    private AppointmentDTO appointmentDTO;

    private Integer scoreForDoctor;
    private Integer scoreForClinic;

}
