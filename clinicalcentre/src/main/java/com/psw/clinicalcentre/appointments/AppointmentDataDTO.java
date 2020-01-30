package com.psw.clinicalcentre.appointments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDataDTO {

    private Integer id;
    private Date date;
    private String time;
    private String type;

}
