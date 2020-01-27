package com.psw.clinicalcentre.appointments;


import com.psw.clinicalcentre.clinics.Clinic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "types_of_appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypesOfAppointments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToOne
    private Clinic clinic;
}
