package com.mvz.vanilla.backend.user;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        Assert.notNull(userRepository, "userRepository cannot be null");
    }

    @Override
    public User findUserByName(String name) throws UserNotFoundException {
        return userRepository.findByName(name)
                .orElseThrow(UserNotFoundException::new);
    }


    @Override
    public User createUser(String name) throws UserAlreadyExistException {
        Optional<User> existingUser = userRepository.findByName(name);
        // TODO: check framework that was used by Johan
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException();
        }

        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }
}
