package com.psw.clinicalcentre.users;

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
public class BasicUserDetailsDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

}
