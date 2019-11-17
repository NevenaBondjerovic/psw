package com.psw.clinicalcentre.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean findUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).isPresent();
    }
}
