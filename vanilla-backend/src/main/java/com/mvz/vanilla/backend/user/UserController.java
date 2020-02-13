package com.mvz.vanilla.backend.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@RestController
@RequestMapping(value = "/backend/api/v1/user",
        produces = "application/json")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
        Assert.notNull(userService, "userService cannot be null");
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public User getUser() {
        try {
            return userService.findUserByName("TestUser");
        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}
