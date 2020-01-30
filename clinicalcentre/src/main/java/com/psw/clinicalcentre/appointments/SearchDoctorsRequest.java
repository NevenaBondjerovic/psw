package com.psw.clinicalcentre.appointments;

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
public class SearchDoctorsRequest {

    @NotNull
    private Integer clinicId;

    @NotNull
    private Date date;

    @NotNull
    private String type;

}
