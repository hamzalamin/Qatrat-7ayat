package com.wora.qatrat7ayat.controllers;

import com.wora.qatrat7ayat.models.DTOs.User.CreateProfileDto;
import com.wora.qatrat7ayat.models.DTOs.User.ProfileDto;
import com.wora.qatrat7ayat.models.DTOs.User.UpdateProfileDto;
import com.wora.qatrat7ayat.services.INTER.IProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final IProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileDto> create(@Valid @RequestBody CreateProfileDto createProfileDto){
        return new ResponseEntity<>(profileService.create(createProfileDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> update(
            @Valid @RequestBody UpdateProfileDto updateProfileDto,
            @PathVariable Long id
    ){
        return new ResponseEntity<>(profileService.update(updateProfileDto, id), HttpStatus.OK);
    }

}
