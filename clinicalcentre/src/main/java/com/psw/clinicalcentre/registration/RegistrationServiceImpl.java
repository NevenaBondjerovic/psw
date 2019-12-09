package com.psw.clinicalcentre.registration;

import com.psw.clinicalcentre.config.EmailMessages;
import com.psw.clinicalcentre.exceptions.AlreadyExistException;
import com.psw.clinicalcentre.exceptions.BadRequestException;
import com.psw.clinicalcentre.exceptions.NotFoundException;
import com.psw.clinicalcentre.users.User;
import com.psw.clinicalcentre.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.psw.clinicalcentre.config.EmailMessages.*;
import static java.lang.Boolean.FALSE;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    private static final String SUBJECT = "Clinical centre registration";
    private static final String USER_ALREADY_EXISTS_ERROR_MESSAGE = "Provided email already exists, please choose another one.";
    private static final String NOT_FOUND_ERROR_MESSAGE = "Registration request not found.";
    private static final String REASON_NOT_ADDED_ERROR_MESSAGE = "When the request is rejected, reason must be added. " +
            "Please add the reason for rejecting the request.";
    private static final String ACTIVATION_LINK = "http://localhost:4200";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SimpleMailMessage template;

    @Override
    @Transactional
    public void registerUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent())
            throw new AlreadyExistException(USER_ALREADY_EXISTS_ERROR_MESSAGE);

        userRepository.save(user);
        registrationRepository.save(new RegistrationRequest(
                user,false, false, null));
    }

    @Override
    public Set<User> findUnprocessedRequests() {
        return registrationRepository.findByProcessed(FALSE).stream()
                .map(RegistrationRequest::getUser).collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public void acceptRegistrationRequest(AcceptRejectRequest request) {
        if(registrationRepository.findByUserUsername(request.getUsername()).isPresent()) {
            String text = String.format(template.getText(), acceptedMessage(ACTIVATION_LINK));
            registrationRepository.acceptRegistrationRequest(request.getUsername());
            //sendSimpleMessage(request.getUsername(), SUBJECT, text);
        } else {
            throw new NotFoundException(NOT_FOUND_ERROR_MESSAGE);
        }
    }

    @Transactional
    @Override
    public void rejectRegistrationRequest(AcceptRejectRequest request) {
        if(request.getDeclineReason() == null || request.getDeclineReason().isEmpty())
            throw new BadRequestException(REASON_NOT_ADDED_ERROR_MESSAGE);
        if(registrationRepository.findByUserUsername(request.getUsername()).isPresent()) {
            String text = String.format(template.getText(), rejectedMessage(request.getDeclineReason()));
            registrationRepository.rejectRegistrationRequest(request.getUsername(), request.getDeclineReason());
            //sendSimpleMessage(request.getUsername(), SUBJECT, text);
        } else {
            throw new NotFoundException(NOT_FOUND_ERROR_MESSAGE);
        }
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

}
