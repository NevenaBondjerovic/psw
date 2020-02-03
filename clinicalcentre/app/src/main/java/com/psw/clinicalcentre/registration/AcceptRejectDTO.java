package com.psw.clinicalcentre.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcceptRejectDTO {

    @NotBlank
    private String username;

    @NotNull
    private Boolean approved;
    private String declineReason;

}
