package com.psw.clinicalcentre.clinics;


import com.psw.clinicalcentre.appointments.TypesOfAppointments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PRICELIST")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pricelist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private TypesOfAppointments type;

    /**
     * Price is shown in euros.
     */
    private Integer price;

    /**
     * Discount is shown in percentage.
     */
    private Integer discount;

    @OneToOne
    private Clinic clinic;
}
