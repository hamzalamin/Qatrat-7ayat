package com.wora.qatrat7ayat.security.exception;

public class UserProfileNotFound extends RuntimeException {
    public UserProfileNotFound(Object email) {
        super("User with email " + email + "is suspended, you can call the support");
    }
}
