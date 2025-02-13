package com.wora.qatrat7ayat.controllers;

import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.UpdateProfileDto;
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

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> update(
            @Valid @RequestBody UpdateProfileDto updateProfileDto,
            @PathVariable Long id
    ){
        return new ResponseEntity<>(profileService.update(updateProfileDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        profileService.delete(id);
        return new ResponseEntity<>("Profile with id : " + id + " Deleted Successfully!", HttpStatus.OK);
    }
}
