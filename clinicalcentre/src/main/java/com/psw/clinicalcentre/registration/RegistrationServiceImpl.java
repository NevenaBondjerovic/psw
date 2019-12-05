package com.psw.clinicalcentre.registration;

import com.psw.clinicalcentre.exceptions.AlreadyExistException;
import com.psw.clinicalcentre.users.User;
import com.psw.clinicalcentre.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    private static final String USER_ALREADY_EXISTS = "Provided email already exists, please choose another one.";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    @Transactional
    public void registerUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent())
            throw new AlreadyExistException(USER_ALREADY_EXISTS);

        userRepository.save(user);
        registrationRepository.save(new RegistrationRequest(
                user,false, false, null));
    }
}
