package com.psw.clinicalcentre.users;

import com.psw.clinicalcentre.exceptions.AlreadyExistException;
import com.psw.clinicalcentre.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "Provided username or password does not exist.";
    private static final String USER_ALREADY_EXISTS = "Provided email already exists, please choose another one.";

    @Autowired
    private UserRepository userRepository;

    @Override
    public void findUser(String username, String password) {
        userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_ERROR_MESSAGE));
    }

    @Override
    public void registerUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent())
            throw new AlreadyExistException(USER_ALREADY_EXISTS);

        userRepository.save(user);
    }
}
