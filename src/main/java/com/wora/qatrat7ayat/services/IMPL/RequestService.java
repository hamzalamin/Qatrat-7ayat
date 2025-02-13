package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.models.DTOs.action.request.EmbeddedRequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.RequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.UpdateRequestDto;
import com.wora.qatrat7ayat.services.INTER.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService implements IRequestService {
    @Override
    public RequestDto create(EmbeddedRequestDto createRequestDto) {
        return null;
    }

    @Override
    public RequestDto findById(Long aLong) {
        return null;
    }

    @Override
    public RequestDto update(UpdateRequestDto updateRequestDto, Long aLong) {
        return null;
    }

    @Override
    public List<RequestDto> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public void delete(Long aLong) {

    }


}
