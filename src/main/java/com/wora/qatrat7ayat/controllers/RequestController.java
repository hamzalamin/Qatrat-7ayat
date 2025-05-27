package com.wora.qatrat7ayat.controllers;

import com.wora.qatrat7ayat.models.DTOs.action.request.CreateRequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.RequestDto;
import com.wora.qatrat7ayat.services.inter.IRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/blood-requests")
@RequiredArgsConstructor
public class RequestController {
    private final IRequestService requestService;

    @PostMapping
    public ResponseEntity<RequestDto> create(@RequestBody @Valid CreateRequestDto createRequestDto){
        return new ResponseEntity<>(requestService.create(createRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<RequestDto>> findAll(@RequestParam int pageNumber, @RequestParam int size) {
        Page<RequestDto> requests = requestService.findAllPage(pageNumber, size);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

}
