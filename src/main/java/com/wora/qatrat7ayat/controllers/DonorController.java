package com.wora.qatrat7ayat.controllers;

import com.wora.qatrat7ayat.models.DTOs.action.donor.CreateDonorDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.DonorDto;
import com.wora.qatrat7ayat.services.INTER.IDonorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/donor")
@RequiredArgsConstructor
public class DonorController {
    private final IDonorService donorService;

    @PostMapping
    public ResponseEntity<DonorDto> create(@RequestBody @Valid CreateDonorDto createDonorDto){
        System.out.println("diro chi 7el hhhhhhhhhhhhhh" + createDonorDto);
        return new ResponseEntity<>(donorService.create(createDonorDto), HttpStatus.CREATED);
    }
}
