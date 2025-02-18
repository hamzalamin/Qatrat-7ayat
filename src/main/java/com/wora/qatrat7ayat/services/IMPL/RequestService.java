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
    private final IProfileService profileService;
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
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }


}
