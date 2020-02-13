package com.mvz.vanilla.backend.user;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findUserByName(String name) throws UserNotFoundException;

    User createUser(String name) throws UserAlreadyExistException;

}
