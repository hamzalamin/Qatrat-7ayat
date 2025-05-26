package com.wora.qatrat7ayat.security.controller;

import com.wora.qatrat7ayat.security.DTO.SignupResponse;
import com.wora.qatrat7ayat.security.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final IAuthService service;

    @GetMapping
    public ResponseEntity<List<SignupResponse>> getAll(){
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }
}
