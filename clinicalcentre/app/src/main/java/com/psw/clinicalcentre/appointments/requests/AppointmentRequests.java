package com.psw.clinicalcentre.appointments.requests;

import com.psw.clinicalcentre.appointments.Appointment;
import com.psw.clinicalcentre.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "APPOINTMENT_REQUESTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Appointment appointment;

    @OneToOne
    private User admin;

    @OneToOne
    private User user;

    private Boolean approvedByAdmin;
    private Boolean accepted;

}
