package com.wora.qatrat7ayat.services.impl;

import com.wora.qatrat7ayat.mappers.DonorMapper;
import com.wora.qatrat7ayat.models.DTOs.action.donor.CreateDonorDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.DonorDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.UpdateDonorDto;
import com.wora.qatrat7ayat.models.entities.Donor;
import com.wora.qatrat7ayat.models.entities.Hospital;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.repositories.DonorRepository;
import com.wora.qatrat7ayat.security.services.IAuthService;
import com.wora.qatrat7ayat.services.inter.IDonorService;
import com.wora.qatrat7ayat.services.inter.IHospitalService;
import com.wora.qatrat7ayat.services.inter.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonorService implements IDonorService {
    private final DonorRepository donorRepository;
    private final DonorMapper donorMapper;
    private final IAuthService profileService;
    private final IUserService userService;
    private final IHospitalService hospitalService;

    @Override
    public DonorDto create(CreateDonorDto createDonorDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User donor;

        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails userDetails) {
            String email = userDetails.getUsername();
            donor = profileService.getUserByEmail(email);
        } else {
            donor = userService.createUserEntity(createDonorDto.getProfile());
        }
        Hospital hospital = hospitalService.findHospitalEntity(createDonorDto.getDonor().getHospitalId());
        Donor savedDonor = donorMapper.toEntity(createDonorDto.getDonor());
        savedDonor.setHospital(hospital);
        savedDonor.setMessage(createDonorDto.getDonor().getMessage());
        savedDonor.setUser(donor);
        savedDonor.setAvailabilityPeriod(createDonorDto.getDonor().getAvailabilityPeriod());
        return donorMapper.toDto(donorRepository.save(savedDonor));
    }

    @Override
    public DonorDto findById(Long id) {
        return null;
    }

    @Override
    public DonorDto update(UpdateDonorDto updateDonorDto, Long id) {
        return null;
    }

    @Override
    public List<DonorDto> findAll(Integer pageNumber, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return donorRepository.findAll(pageRequest).stream()
                .map(donorMapper::toDto)
                .toList();
    }

    @Override
    public Page<DonorDto> findAllPage(Integer pageNumber, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return donorRepository.findAll(pageRequest).map(donorMapper::toDto);
    }


    @Override
    public void delete(Long id) {

    }
}
