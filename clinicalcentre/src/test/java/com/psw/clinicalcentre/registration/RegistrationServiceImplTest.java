package com.psw.clinicalcentre.registration;

import com.psw.clinicalcentre.exceptions.AlreadyExistException;
import com.psw.clinicalcentre.exceptions.BadRequestException;
import com.psw.clinicalcentre.exceptions.NotFoundException;
import com.psw.clinicalcentre.users.User;
import com.psw.clinicalcentre.users.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class RegistrationServiceImplTest {

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Mock
    private AcceptRejectDTO acceptRejectDTO;

    @Mock
    private SimpleMailMessage template;

    private Set<RegistrationRequest> registrationRequests;
    private String username;
    private RegistrationRequest registrationRequest;

    @Before
    public void setUp() throws Exception {
        username = "username";
        registrationRequest = new RegistrationRequest(user,false, false, null);
        registrationRequests = new HashSet<>();
        registrationRequests.add(new RegistrationRequest(user,false, false, null));
        registrationRequests.add(new RegistrationRequest(user,true, false, null));
    }

    @Test
    public void registerUser() {
        when(user.getUsername()).thenReturn(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);
        when(registrationRepository.save(registrationRequest)).thenReturn(registrationRequest);
        registrationService.registerUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test(expected = AlreadyExistException.class)
    public void registerUser_userAlreadyExists() {
        when(user.getUsername()).thenReturn(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        registrationService.registerUser(user);
        verify(userRepository, times(0)).save(user);
        verify(registrationRepository, times(0)).save(registrationRequest);
    }

    @Test
    public void findUnprocessedRequests() {
        when(registrationRepository.findByProcessed(Boolean.FALSE)).thenReturn(registrationRequests);
        Set<User> returnValue = registrationService.findUnprocessedRequests();
        assertEquals(1, returnValue.size());
    }

    @Test
    public void acceptRegistrationRequest() {
        when(acceptRejectDTO.getUsername()).thenReturn(username);
        when(registrationRepository.findByUserUsername(username)).thenReturn(Optional.of(registrationRequest));
        when(template.getText()).thenReturn("%s");
        when(user.getId()).thenReturn(12345);
        when(acceptRejectDTO.getUsername()).thenReturn(username);
        doNothing().when(registrationRepository).acceptRegistrationRequest(username);
        registrationService.acceptRegistrationRequest(acceptRejectDTO);
        verify(registrationRepository, times(1)).acceptRegistrationRequest(username);
    }

    @Test(expected = NotFoundException.class)
    public void acceptRegistrationRequest_notFound() {
        when(acceptRejectDTO.getUsername()).thenReturn(username);
        when(registrationRepository.findByUserUsername(username)).thenReturn(Optional.empty());
        registrationService.acceptRegistrationRequest(acceptRejectDTO);
        verify(registrationRepository, times(0)).acceptRegistrationRequest(username);
    }

    @Test
    public void rejectRegistrationRequest() {
        String declineReason = "Some decline reason.";
        when(acceptRejectDTO.getDeclineReason()).thenReturn(declineReason);
        when(acceptRejectDTO.getUsername()).thenReturn(username);
        when(registrationRepository.findByUserUsername(username)).thenReturn(Optional.of(registrationRequest));
        when(template.getText()).thenReturn("%s");
        doNothing().when(registrationRepository).rejectRegistrationRequest(username, declineReason);
        registrationService.rejectRegistrationRequest(acceptRejectDTO);
        verify(registrationRepository, times(1)).rejectRegistrationRequest(username, declineReason);
    }

    @Test(expected = BadRequestException.class)
    public void rejectRegistrationRequest_badRequest_declineReasonIsNull() {
        when(acceptRejectDTO.getDeclineReason()).thenReturn(null);
        registrationService.rejectRegistrationRequest(acceptRejectDTO);
    }

    @Test(expected = BadRequestException.class)
    public void rejectRegistrationRequest_badRequest_declineReasonIsEmpty() {
        when(acceptRejectDTO.getDeclineReason()).thenReturn("");
        registrationService.rejectRegistrationRequest(acceptRejectDTO);
    }

    @Test(expected = NotFoundException.class)
    public void rejectRegistrationRequest_notFound() {
        String declineReason = "Some decline reason.";
        when(acceptRejectDTO.getDeclineReason()).thenReturn(declineReason);
        when(acceptRejectDTO.getUsername()).thenReturn(username);
        when(registrationRepository.findByUserUsername(username)).thenReturn(Optional.empty());
        registrationService.rejectRegistrationRequest(acceptRejectDTO);
        verify(registrationRepository, times(0)).rejectRegistrationRequest(username, declineReason);
    }
}