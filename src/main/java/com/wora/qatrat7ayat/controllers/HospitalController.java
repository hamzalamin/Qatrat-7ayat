package com.wora.qatrat7ayat.controllers;


import com.wora.qatrat7ayat.models.DTOs.hospital.HospitalDto;
import com.wora.qatrat7ayat.services.INTER.IHospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospitals")
@RequiredArgsConstructor
public class HospitalController {
    private final IHospitalService hospitalService;

    @GetMapping
    public ResponseEntity<List<HospitalDto>> findAll(@RequestParam int pageNumber, int size){
        List<HospitalDto> hospitals = hospitalService.findAll(pageNumber, size);
        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }
}
