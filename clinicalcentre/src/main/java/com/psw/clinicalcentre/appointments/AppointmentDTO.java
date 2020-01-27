package com.psw.clinicalcentre.appointments;

import com.psw.clinicalcentre.clinics.ClinicDTO;
import com.psw.clinicalcentre.clinics.HallDTO;
import com.psw.clinicalcentre.clinics.PricelistDTO;
import com.psw.clinicalcentre.users.BasicUserDetailsDTO;
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
public class AppointmentDTO {

    @NotNull
    private Integer id;

    @NotNull
    private Date dateOfAppointment;

    @NotNull
    private String timeOfAppointment;

    @NotNull
    private ClinicDTO clinic;

    @NotNull
    private HallDTO hall;

    @NotNull
    private BasicUserDetailsDTO doctor;

    @NotNull
    private TypesOfAppointmentsDTO type;

    @NotNull
    private PricelistDTO pricelist;

    private BasicUserDetailsDTO scheduledFor;

}
