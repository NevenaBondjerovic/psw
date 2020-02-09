package com.psw.clinicalcentre.scores;

import com.psw.clinicalcentre.appointments.Appointment;
import com.psw.clinicalcentre.clinics.Clinic;
import com.psw.clinicalcentre.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SCORES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private User doctor;

    private Integer doctorScore;

    @OneToOne
    private Clinic clinic;

    private Integer clinicScore;

    @OneToOne
    private User user;

    @OneToOne
    private Appointment appointment;
}
