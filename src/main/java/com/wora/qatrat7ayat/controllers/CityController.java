package com.wora.qatrat7ayat.controllers;


import com.wora.qatrat7ayat.models.DTOs.city.CityDto;
import com.wora.qatrat7ayat.services.inter.ICityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityController {
    private final ICityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDto>> findAll(@RequestParam int pageNumber, int size){
        List<CityDto> citys = cityService.findAll(pageNumber, size);
        return new ResponseEntity<>(citys, HttpStatus.OK);
    }
}
