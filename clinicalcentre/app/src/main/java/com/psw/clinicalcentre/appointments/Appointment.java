package com.psw.clinicalcentre.appointments;

import com.psw.clinicalcentre.clinics.Clinic;
import com.psw.clinicalcentre.clinics.Hall;
import com.psw.clinicalcentre.clinics.Pricelist;
import com.psw.clinicalcentre.types.TypesOfAppointments;
import com.psw.clinicalcentre.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "APPOINTMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date dateOfAppointment;
    private String timeOfAppointment;

    @OneToOne
    private Clinic clinic;

    @OneToOne
    private Hall hall;

    @OneToOne
    private User doctor;

    @OneToOne
    private TypesOfAppointments type;

    @OneToOne
    private Pricelist pricelist;

    @OneToOne
    private User scheduledFor;

}
