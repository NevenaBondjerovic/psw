package com.psw.clinicalcentre.clinics;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Table(name = "CLINICS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String address;

    @Max(5)
    private Integer score;

}
