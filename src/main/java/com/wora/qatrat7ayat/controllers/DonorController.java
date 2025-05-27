package com.wora.qatrat7ayat.controllers;

import com.wora.qatrat7ayat.models.DTOs.action.donor.CreateDonorDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.DonorDto;
import com.wora.qatrat7ayat.services.inter.IDonorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/donors")
@RequiredArgsConstructor
public class DonorController {
    private final IDonorService donorService;

    @PostMapping
    public ResponseEntity<DonorDto> create(@RequestBody @Valid CreateDonorDto createDonorDto){
        return new ResponseEntity<>(donorService.create(createDonorDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<DonorDto>> findAll(@RequestParam int pageNumber, @RequestParam int size) {
        Page<DonorDto> donors = donorService.findAllPage(pageNumber, size);
        return new ResponseEntity<>(donors, HttpStatus.OK);
    }
}
