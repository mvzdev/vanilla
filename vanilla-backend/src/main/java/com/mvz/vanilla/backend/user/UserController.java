package com.mvz.vanilla.backend.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(
        value = "/backend/api/v1/users",
        produces = "application/json")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestParam String username) {
        try {
            userService.createUser(username);
        } catch (UserAlreadyExistException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUser() {
        return userService.findAllUser();
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUsers(@PathVariable String username) {
        try {
            return userService.findUserByName(username);
        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }


}



