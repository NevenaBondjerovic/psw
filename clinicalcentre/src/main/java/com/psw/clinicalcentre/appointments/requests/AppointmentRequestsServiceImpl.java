package com.psw.clinicalcentre.appointments.requests;

import com.psw.clinicalcentre.appointments.Appointment;
import com.psw.clinicalcentre.appointments.AppointmentRepository;
import com.psw.clinicalcentre.exceptions.NotFoundException;
import com.psw.clinicalcentre.users.User;
import com.psw.clinicalcentre.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class AppointmentRequestsServiceImpl implements AppointmentRequestsService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentRequestsRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void save(Integer appointmentId, Integer userId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found."));
        User admin = userRepository.findClinicAdminByClinicId(appointment.getClinic().getId())
                .orElseThrow(() -> new NotFoundException("Admin of the clinic not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found."));
        AppointmentRequests request = AppointmentRequests.builder().appointment(appointment)
                .admin(admin).user(user).approvedByAdmin(null).accepted(null).build();
        repository.save(request);
        appointment.setScheduledFor(user);
        appointmentRepository.save(appointment);
    }

    @Override
    public Set<AppointmentRequests> findAllUnprocessedByAdmin(Integer adminId) {
        return repository.findUnprocessedByAdmin(adminId);
    }
}
