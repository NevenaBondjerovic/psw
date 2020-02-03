package com.psw.clinicalcentre.clinics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PricelistDTO {

    @NotNull
    private Integer id;

    @NotNull
    private Integer price;

    @NotNull
    private Integer discount;

}
