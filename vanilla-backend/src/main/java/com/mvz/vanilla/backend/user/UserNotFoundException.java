package com.mvz.vanilla.backend.user;

class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}
