package com.psw.clinicalcentre.users;

import com.psw.clinicalcentre.exceptions.BadRequestException;
import com.psw.clinicalcentre.exceptions.NotFoundException;
import com.psw.clinicalcentre.registration.RegistrationRepository;
import com.psw.clinicalcentre.registration.RegistrationRequest;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private User user;

    @Mock
    private RegistrationRequest registrationRequest;

    private String username;
    private String password;
    private Integer userId;

    @Before
    public void setUp() throws Exception {
        username = "username";
        password = "pass";
        userId = 12345;
    }

    @Test
    public void findUser() {
        when(userRepository.findByUsernameAndPassword(username, password))
                .thenReturn(Optional.of(user));
        userService.findUser(username, password);
        verify(userRepository, times(1)).findByUsernameAndPassword(username, password);
    }

    @Test(expected = NotFoundException.class)
    public void findUser_userNotFound() {
        when(userRepository.findByUsernameAndPassword(username, password))
                .thenReturn(Optional.empty());
        userService.findUser(username, password);
        verify(userRepository, times(1)).findByUsernameAndPassword(username, password);
    }

    @Test
    public void findById() {
        when(registrationRepository.findByUserId(userId))
                .thenReturn(Optional.of(registrationRequest));
        setUpRegistrationRequestData(TRUE, TRUE, FALSE);
        userService.findById(userId);
        verify(registrationRepository, times(1)).findByUserId(userId);
    }

    @Test(expected = NotFoundException.class)
    public void findById_userNotFound() {
        when(registrationRepository.findByUserId(userId))
                .thenReturn(Optional.empty());
        userService.findById(userId);
        verify(registrationRepository, times(1)).findByUserId(userId);
    }

    @Test(expected = BadRequestException.class)
    public void findById_badRequest_processedIsFalse() {
        when(registrationRepository.findByUserId(userId))
                .thenReturn(Optional.of(registrationRequest));
        setUpRegistrationRequestData(FALSE, TRUE, FALSE);
        userService.findById(userId);
        verify(registrationRepository, times(1)).findByUserId(userId);
    }

    @Test(expected = BadRequestException.class)
    public void findById_badRequest_approvedIsFalse() {
        when(registrationRepository.findByUserId(userId))
                .thenReturn(Optional.of(registrationRequest));
        setUpRegistrationRequestData(TRUE, FALSE, FALSE);
        userService.findById(userId);
        verify(registrationRepository, times(1)).findByUserId(userId);
    }

    @Test(expected = BadRequestException.class)
    public void findById_badRequest_userActivatedIsTrue() {
        when(registrationRepository.findByUserId(userId))
                .thenReturn(Optional.of(registrationRequest));
        setUpRegistrationRequestData(FALSE, FALSE, TRUE);
        userService.findById(userId);
        verify(registrationRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void findByUsername() {
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(user));
        User returnValue = userService.findByUsername(username);
        verify(userRepository, times(1)).findByUsername(username);
        assertEquals(user, returnValue);
    }

    @Test(expected = NotFoundException.class)
    public void findByUsername_userNotFound() {
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.empty());
        User returnValue = userService.findByUsername(username);
        verify(userRepository, times(1)).findByUsername(username);
        assertEquals(null, returnValue);
    }

    @Test
    public void activateAccount() {
        Integer sentId = userId;
        String sentUsername = username;
        String sentPassword = password;
        when(registrationRepository.findByUserUsername(sentUsername))
                .thenReturn(Optional.of(registrationRequest));
        when(registrationRequest.getUser()).thenReturn(user);
        setUpUserData(sentId, sentUsername, sentPassword);
        setUpRegistrationRequestData(TRUE, TRUE, FALSE);
        userService.activateAccount(sentId, sentUsername, sentPassword);
        verify(userRepository, times(1)).save(user);
    }

    @Test(expected = NotFoundException.class)
    public void activateAccount_userNotFound() {
        when(registrationRepository.findByUserUsername(username))
                .thenReturn(Optional.empty());
        userService.activateAccount(userId, username, password);
        verify(userRepository, times(0)).save(user);
    }

    @Test(expected = NotFoundException.class)
    public void activateAccount_notFoundException_wrongUserId() {
        Integer sentId = userId;
        String sentUsername = username;
        String sentPassword = password;
        testIfDataAreCorrect(65432, sentUsername, sentPassword);
        userService.activateAccount(sentId, sentUsername, sentPassword);
        verify(userRepository, times(0)).save(user);
    }

    @Test(expected = NotFoundException.class)
    public void activateAccount_notFoundException_wrongUsername() {
        Integer sentId = userId;
        String sentUsername = username;
        String sentPassword = password;
        testIfDataAreCorrect(sentId, "Unknown username", sentPassword);
        userService.activateAccount(sentId, sentUsername, sentPassword);
        verify(userRepository, times(0)).save(user);
    }

    @Test(expected = NotFoundException.class)
    public void activateAccount_notFoundException_wrongPassword() {
        Integer sentId = userId;
        String sentUsername = username;
        String sentPassword = password;
        testIfDataAreCorrect(sentId, sentUsername, "Unknown pass");
        userService.activateAccount(sentId, sentUsername, sentPassword);
        verify(userRepository, times(0)).save(user);
    }

    @Test(expected = BadRequestException.class)
    public void activateAccount_badRequest_processedIsFalse() {
        testIfRequestIsWaitingForActivation(FALSE, TRUE, FALSE);
    }

    @Test(expected = BadRequestException.class)
    public void activateAccount_badRequest_approvedIsFalse() {
        testIfRequestIsWaitingForActivation(TRUE, FALSE, FALSE);
    }

    @Test(expected = BadRequestException.class)
    public void activateAccount_badRequest_userActivatedIsTrue() {
        testIfRequestIsWaitingForActivation(TRUE, TRUE, TRUE);
    }

    @Test
    public void save() {
        when(user.getId()).thenReturn(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        User returnValue = userService.save(user);
        verify(userRepository, times(1)).save(user);
        assertEquals(user, returnValue);
    }

    @Test(expected = NotFoundException.class)
    public void save_notFoundException() {
        when(user.getId()).thenReturn(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        userService.save(user);
        verify(userRepository, times(0)).save(user);
    }

    private void setUpRegistrationRequestData(Boolean processed, Boolean approved, Boolean userActivated) {
        when(registrationRequest.getProcessed()).thenReturn(processed);
        when(registrationRequest.getApproved()).thenReturn(approved);
        when(registrationRequest.getUser()).thenReturn(user);
        when(user.getActivated()).thenReturn(userActivated);
    }

    private void setUpUserData(Integer uId, String uUsername, String uPassword){
        when(user.getId()).thenReturn(uId);
        when(user.getUsername()).thenReturn(uUsername);
        when(user.getPassword()).thenReturn(uPassword);
    }

    private void testIfDataAreCorrect(Integer sentId, String sentUsername, String sentPassword){
        when(registrationRepository.findByUserUsername(sentUsername))
                .thenReturn(Optional.of(registrationRequest));
        when(registrationRequest.getUser()).thenReturn(user);
        setUpUserData(sentId, sentUsername, sentPassword);
        setUpRegistrationRequestData(TRUE, TRUE, FALSE);;
    }

    private void testIfRequestIsWaitingForActivation(Boolean processed, Boolean approved, Boolean userActivated){
        Integer sentId = userId;
        String sentUsername = username;
        String sentPassword = password;
        when(registrationRepository.findByUserUsername(sentUsername))
                .thenReturn(Optional.of(registrationRequest));
        when(registrationRequest.getUser()).thenReturn(user);
        setUpUserData(sentId, sentUsername, sentPassword);
        setUpRegistrationRequestData(processed, approved, userActivated);
        userService.activateAccount(sentId, sentUsername, sentPassword);
        verify(userRepository, times(0)).save(user);
    }

}