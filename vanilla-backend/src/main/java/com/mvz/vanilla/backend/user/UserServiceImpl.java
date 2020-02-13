package com.mvz.vanilla.backend.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(String name) throws UserAlreadyExistException {
        Optional<User> existingUser = userRepository.findByName(name);
        // TODO: use Vavr library for nicer calls on option
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException("User already exists");
        }

        User user = new User();
        user.setName(name);
        user.setEntityId(UUID.randomUUID().toString());

        User newUser = userRepository.save(user);
        log.info("User created: {}", newUser.toString());
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByName(String name) throws UserNotFoundException {
        return userRepository.findByName(name)
                .orElseThrow(() ->
                    new UserNotFoundException("User not found"));
    }
}
