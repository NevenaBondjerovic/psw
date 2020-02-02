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
public class ApproveRequestDTO {

    @NotNull
    private Integer requestId;

    @NotNull
    private Boolean approved;
}
