package com.psw.clinicalcentre.users;

import com.psw.clinicalcentre.exceptions.BadRequestException;
import com.psw.clinicalcentre.exceptions.NotFoundException;
import com.psw.clinicalcentre.registration.RegistrationRepository;
import com.psw.clinicalcentre.registration.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Boolean.*;

@Service
public class UserServiceImpl implements UserService {

    private static final String USERNAME_OR_PASS_NOT_EXIST_ERROR_MESSAGE = "Provided username or password does not exist.";
    private static final String ACCOUNT_CANNOT_BE_ACTIVATED_ERROR_MESSAGE = "Account cannot be activated.";
    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "User not found.";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public User findUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new NotFoundException(USERNAME_OR_PASS_NOT_EXIST_ERROR_MESSAGE));
    }

    @Override
    public void findById(Integer id) {
        RegistrationRequest request = registrationRepository.findByUserId(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_ERROR_MESSAGE));
        if(!isRequestWaitingForActivation(request))
            throw new BadRequestException(ACCOUNT_CANNOT_BE_ACTIVATED_ERROR_MESSAGE);
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_ERROR_MESSAGE));
    }

    @Transactional
    @Override
    public void activateAccount(Integer id, String username, String password){
        RegistrationRequest request = registrationRepository.findByUserUsername(username)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_ERROR_MESSAGE));

        if(!isDataCorrect(id, username, password, request.getUser()))
            throw new NotFoundException(USER_NOT_FOUND_ERROR_MESSAGE);
        if(!isRequestWaitingForActivation(request))
            throw new BadRequestException(ACCOUNT_CANNOT_BE_ACTIVATED_ERROR_MESSAGE);

        User user = request.getUser();
        user.setActivated(TRUE);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User save(User user){
        userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_ERROR_MESSAGE));
        return userRepository.save(user);
    }

    private Boolean isRequestWaitingForActivation(RegistrationRequest request){
        return request.getProcessed() == TRUE && request.getApproved() == TRUE && request.getUser().getActivated() == FALSE;
    }

    private Boolean isDataCorrect(Integer id, String username, String password, User user){
        return id.equals(user.getId()) && username.equals(user.getUsername()) && password.equals(user.getPassword());
    }

}
