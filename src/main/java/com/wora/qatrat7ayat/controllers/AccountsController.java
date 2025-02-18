package com.wora.qatrat7ayat.controllers;

import com.wora.qatrat7ayat.security.DTO.SignupRequest;
import com.wora.qatrat7ayat.security.DTO.SignupResponse;
import com.wora.qatrat7ayat.services.INTER.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/")
@RequiredArgsConstructor
public class AccountsController {
    private final IUserService userService;

    @PostMapping("/create-account")
    public ResponseEntity<SignupResponse> create(@RequestBody @Valid SignupRequest signupRequest){
        return new ResponseEntity<>(userService.CreateUserAccount(signupRequest), HttpStatus.CREATED);
    }
}
