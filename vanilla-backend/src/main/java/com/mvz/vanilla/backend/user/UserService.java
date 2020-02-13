package com.mvz.vanilla.backend.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findUserByName(String name) throws UserNotFoundException;

    void createUser(String name) throws UserAlreadyExistException;

    List<User> findAllUser();
}
