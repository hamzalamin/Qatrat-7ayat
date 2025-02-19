package com.wora.qatrat7ayat.security.exception.global;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.security.exception.UserSuspendedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerSecurity {
    @ExceptionHandler(UserSuspendedException.class)
    public ResponseEntity<String> handleUserSuspendedException(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
