package com.mvz.vanilla.backend.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void createUser(String name) throws UserAlreadyExistException;

    List<User> findAllUser();

    User findUserByName(String name) throws UserNotFoundException;

    void updateUserName(String oldName, String newName) throws UserNotFoundException;

    }
