package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.mappers.RequestMapper;
import com.wora.qatrat7ayat.models.DTOs.action.request.CreateRequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.RequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.UpdateRequestDto;
import com.wora.qatrat7ayat.models.entities.Hospital;
import com.wora.qatrat7ayat.models.entities.Request;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.repositories.RequestRepository;
import com.wora.qatrat7ayat.services.INTER.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService implements IRequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final IUserService userService;
    private final IHospitalService hospitalService;

    @Override
    @Transactional
    public RequestDto create(CreateRequestDto createRequestDto) {
        Hospital hospital = hospitalService.findHospitalEntity(createRequestDto.getRequest().getHospitalId());

        User savedUser = userService.createUserEntity(createRequestDto.getProfile());

        Request request = requestMapper.toEntity(createRequestDto.getRequest());
        request.setMessage(createRequestDto.getRequest().getMessage());
        request.setUser(savedUser);
        request.setHospital(hospital);
        request.setUser(savedUser);
        request.setMessage(createRequestDto.getRequest().getMessage());
        Request savedRequest = requestRepository.save(request);

        return requestMapper.toDto(savedRequest);
    }


    @Override
    public RequestDto findById(Long id) {
        return null;
    }

    @Override
    public RequestDto update(UpdateRequestDto updateRequestDto, Long id) {
        return null;
    }

    @Override
    public List<RequestDto> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }


}
