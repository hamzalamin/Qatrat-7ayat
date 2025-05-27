package com.wora.qatrat7ayat.services.impl;

import com.wora.qatrat7ayat.mappers.RequestMapper;
import com.wora.qatrat7ayat.models.DTOs.action.request.CreateRequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.RequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.UpdateRequestDto;
import com.wora.qatrat7ayat.models.entities.Hospital;
import com.wora.qatrat7ayat.models.entities.Request;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.repositories.RequestRepository;
import com.wora.qatrat7ayat.security.services.IAuthService;
import com.wora.qatrat7ayat.services.inter.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService implements IRequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final IAuthService profileService;
    private final IUserService userService;
    private final IHospitalService hospitalService;

    @Override
    @Transactional
    public RequestDto create(CreateRequestDto createRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User requestUser;

        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails userDetails) {
            String email = userDetails.getUsername();
            requestUser = profileService.getUserByEmail(email);
        } else {
            requestUser = userService.createUserEntity(createRequestDto.getProfile());
        }
        Hospital hospital = hospitalService.findHospitalEntity(createRequestDto.getRequest().getHospitalId());

        Request request = requestMapper.toEntity(createRequestDto.getRequest());
        request.setUser(requestUser);
        request.setHospital(hospital);
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
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return requestRepository.findAll(pageRequest).stream()
                .map(requestMapper::toDto)
                .toList();
    }


    @Override
    public Page<RequestDto> findAllPage(Integer pageNumber, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return requestRepository.findAll(pageRequest).map(requestMapper::toDto);
    }

    @Override
    public void delete(Long id) {

    }


}
