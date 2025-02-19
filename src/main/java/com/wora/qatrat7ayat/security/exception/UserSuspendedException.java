package com.wora.qatrat7ayat.security.exception;

public class UserSuspendedException extends RuntimeException {
    public UserSuspendedException(Object email) {
        super("User with email " + email + "is suspended, you can call the support");
    }
}
