package com.wora.qatrat7ayat.controllers;

import com.wora.qatrat7ayat.models.DTOs.user.CreateUserAccountDto;
import com.wora.qatrat7ayat.security.DTO.SignupResponse;
import com.wora.qatrat7ayat.services.INTER.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/")
@RequiredArgsConstructor
public class AccountsController {
    private final IUserService userService;

    @PostMapping("/create-account")
    public ResponseEntity<SignupResponse> create(@RequestBody @Valid CreateUserAccountDto signupRequest) {
        return new ResponseEntity<>(userService.createUserAccount(signupRequest), HttpStatus.CREATED);
    }

    //patch katreplaci gha state f lentity makatupdatich ela gaa3 entity
    @PatchMapping("/{id}/suspend-toggle")
    public ResponseEntity<String> toggleUserSuspension(@PathVariable Long id) {
        boolean isSuspended = userService.toggleSuspension(id);
        String message = isSuspended ? "User has been unsuspended." : "User has been suspended.";
        return ResponseEntity.ok(message);
    }
}
