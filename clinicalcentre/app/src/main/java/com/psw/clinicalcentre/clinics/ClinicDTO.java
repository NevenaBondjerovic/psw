package com.psw.clinicalcentre.clinics;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @Max(5)
    private Integer score;

}
