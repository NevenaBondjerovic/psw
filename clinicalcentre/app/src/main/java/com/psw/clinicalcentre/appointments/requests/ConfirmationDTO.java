package com.psw.clinicalcentre.appointments.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmationDTO {

    @NotNull
    private Integer appointmentId;

    @NotNull
    private Boolean accepted;

}
