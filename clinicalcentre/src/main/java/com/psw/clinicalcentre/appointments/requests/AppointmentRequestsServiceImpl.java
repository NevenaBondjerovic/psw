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

import static com.psw.clinicalcentre.config.EmailMessages.*;

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

    @Override
    @Transactional
    public void acceptAppointmentRequest(Integer appointmentId) {
        AppointmentRequests request = repository.findByAppointment(appointmentId)
                .orElseThrow(() -> new NotFoundException("Request not found."));
        request.setAccepted(Boolean.TRUE);
        repository.save(request);
    }

    @Override
    @Transactional
    public void declineAppointmentRequest(Integer appointmentId) {
        AppointmentRequests request = repository.findByAppointment(appointmentId)
                .orElseThrow(() -> new NotFoundException("Request not found."));
        request.setAccepted(Boolean.FALSE);
        repository.save(request);

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found."));
        appointment.setScheduledFor(null);
        appointmentRepository.save(appointment);

    }

    @Override
    @Transactional
    public void approveRequestByAdmin(Integer requestId) {
        AppointmentRequests request = repository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Request not found."));
        request.setApprovedByAdmin(Boolean.TRUE);
        repository.save(request);

        String text = String.format(template.getText(), appointmentRequestApproved(request));
        sendSimpleMessage(request.getUser().getUsername(), SUBJECT, text);
    }

    @Override
    @Transactional
    public void declineRequestByAdmin(Integer requestId) {
        AppointmentRequests request = repository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Request not found."));
        request.setApprovedByAdmin(Boolean.FALSE);
        repository.save(request);

        Appointment appointment = appointmentRepository.findById(request.getAppointment().getId())
                .orElseThrow(() -> new NotFoundException("Appointment not found."));
        appointment.setScheduledFor(null);
        appointmentRepository.save(appointment);

        String text = String.format(template.getText(), appointmentRequestDeclined(request));
        sendSimpleMessage(request.getUser().getUsername(), SUBJECT, text);
    }


    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
