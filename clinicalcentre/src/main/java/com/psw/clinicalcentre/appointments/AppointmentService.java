package com.psw.clinicalcentre.appointments;

import java.util.Set;

public interface AppointmentService {

    Set<Appointment> findAllAvailable();
}
