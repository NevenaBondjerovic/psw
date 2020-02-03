package com.psw.clinicalcentre.clinics;

import com.psw.clinicalcentre.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CLINICS_DOCTORS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicsDoctors {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private User user;

    @OneToOne
    private Clinic clinic;

}
