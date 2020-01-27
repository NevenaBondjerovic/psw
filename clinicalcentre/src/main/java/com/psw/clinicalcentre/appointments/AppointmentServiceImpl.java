package com.psw.clinicalcentre.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Set<Appointment> findAllAvailable() {
        return appointmentRepository.findAllByScheduledFor(null);
    }
}
