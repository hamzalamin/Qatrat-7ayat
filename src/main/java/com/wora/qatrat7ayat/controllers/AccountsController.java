package com.wora.qatrat7ayat.controllers;

import com.wora.qatrat7ayat.models.DTOs.account.AccountDto;
import com.wora.qatrat7ayat.models.DTOs.account.CreateUserAccountDto;
import com.wora.qatrat7ayat.models.DTOs.account.UpdateUserAccountDto;
import com.wora.qatrat7ayat.security.DTO.SignupResponse;
import com.wora.qatrat7ayat.services.inter.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/accounts")
@RequiredArgsConstructor
public class AccountsController {
    private final IAccountService accountService;

    @PostMapping
    public ResponseEntity<SignupResponse> create(@RequestBody @Valid CreateUserAccountDto signupRequest) {
        return new ResponseEntity<>(accountService.create(signupRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> toggleUserSuspension(@PathVariable Long id) {
        boolean isSuspended = accountService.toggleSuspension(id);
        String message = isSuspended ? "User has been unsuspended." : "User has been suspended.";
        return ResponseEntity.ok(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SignupResponse> update(
            @RequestBody @Valid UpdateUserAccountDto updateUserAccountDto,
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(accountService.update(updateUserAccountDto, id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AccountDto>> findAll(@RequestParam int pageNumber, int size){
        Page<AccountDto> articles = accountService.findAllPage(pageNumber, size);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id
    ){
        accountService.delete(id);
        return new ResponseEntity<>("ARTICLE with id: " + id + " deleted successfully !!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignupResponse> findById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }
}
