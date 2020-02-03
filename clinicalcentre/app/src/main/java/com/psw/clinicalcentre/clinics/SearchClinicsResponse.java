package com.psw.clinicalcentre.clinics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchClinicsResponse {

    private Integer appointmentId;
    private Date date;
    private Integer clinicId;
    private String clinicName;
    private String clinicAddress;
    private Integer clinicScore;
    private Integer price;
    private String typeName;

}
