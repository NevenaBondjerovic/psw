package com.psw.clinicalcentre.appointments;

import com.psw.clinicalcentre.exceptions.NotFoundException;
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

    @Override
    public Appointment findById(Integer id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Appointment not found."));
    }

    @Override
    public Set<Appointment> findAllFutureForDoctor(Integer id) {
        return appointmentRepository.findAllFutureAppointmentsForDoctor(id);
    }
}
