package com.wora.qatrat7ayat.exceptions;

public class UnknownStatusException extends RuntimeException{

    public UnknownStatusException(Object obj) {
        super("This " + obj);
    }

}
