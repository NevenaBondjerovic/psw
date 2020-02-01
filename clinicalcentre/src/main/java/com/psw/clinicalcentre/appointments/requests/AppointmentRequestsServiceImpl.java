package com.psw.clinicalcentre.appointments.requests;

import com.psw.clinicalcentre.appointments.Appointment;
import com.psw.clinicalcentre.appointments.AppointmentRepository;
import com.psw.clinicalcentre.exceptions.NotFoundException;
import com.psw.clinicalcentre.users.User;
import com.psw.clinicalcentre.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

import static com.psw.clinicalcentre.config.EmailMessages.appointmentRequestArrived;

@Service
public class AppointmentRequestsServiceImpl implements AppointmentRequestsService {

    private static final String SUBJECT = "Clinical centre appointment requests";

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentRequestsRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SimpleMailMessage template;

    @Override
    @Transactional
    public void save(Integer appointmentId, Integer userId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found."));
        User clinicAdmin = userRepository.findClinicAdminByClinicId(appointment.getClinic().getId())
                .orElseThrow(() -> new NotFoundException("Admin of the clinic not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found."));
        AppointmentRequests request = AppointmentRequests.builder().appointment(appointment)
                .admin(clinicAdmin).user(user).approvedByAdmin(null).accepted(null).build();
        repository.save(request);
        appointment.setScheduledFor(user);
        appointmentRepository.save(appointment);

        User admin = userRepository.findByType("CLINICAL_CENTRE_ADMIN")
                .orElseThrow(() -> new NotFoundException("Admin not found."));
        String text = String.format(template.getText(), appointmentRequestArrived(request));
        sendSimpleMessage(admin.getUsername(), SUBJECT, text);

    }

    @Override
    public Set<AppointmentRequests> findAllUnprocessedByAdmin(Integer adminId) {
        return repository.findUnprocessedByAdmin(adminId);
    }


    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
