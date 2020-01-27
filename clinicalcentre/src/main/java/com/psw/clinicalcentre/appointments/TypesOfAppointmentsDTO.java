package com.psw.clinicalcentre.appointments;

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
public class TypesOfAppointmentsDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String name;

}
