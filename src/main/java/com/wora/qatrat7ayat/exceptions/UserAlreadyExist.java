package com.wora.qatrat7ayat.exceptions;

public class UserAlreadyExist extends RuntimeException{

    public UserAlreadyExist(Object email) {
        super("This " + email + " Already Exist");
    }

}
