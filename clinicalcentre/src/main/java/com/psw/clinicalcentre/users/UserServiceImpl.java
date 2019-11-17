package com.psw.clinicalcentre.users;

import com.psw.clinicalcentre.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "Provided username or password does not exist.";

    @Autowired
    private UserRepository userRepository;

    @Override
    public void findUser(String username, String password) {
        userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_ERROR_MESSAGE));
    }
}
