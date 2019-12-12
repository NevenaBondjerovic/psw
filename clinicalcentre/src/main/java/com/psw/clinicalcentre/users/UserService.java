package com.psw.clinicalcentre.users;

public interface UserService {

    void findUser(String username, String password);
    void findById(Integer id);
    User findByUsername(String username);
    void activateAccount(Integer id, String username, String password);
}
