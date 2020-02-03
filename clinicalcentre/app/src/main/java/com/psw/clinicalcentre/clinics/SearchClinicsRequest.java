package com.psw.clinicalcentre.clinics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchClinicsRequest {

    @NotNull
    private Date date;

    @NotNull
    private String type;
}
