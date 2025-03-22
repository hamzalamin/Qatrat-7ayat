package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.mappers.DonorMapper;
import com.wora.qatrat7ayat.models.DTOs.action.donor.CreateDonorDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.DonorDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.EmbeddedDonorDetailsDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.EmbeddedDonorDto;
import com.wora.qatrat7ayat.models.DTOs.city.EmbeddedCityDto;
import com.wora.qatrat7ayat.models.DTOs.hospital.EmbeddedHospitalDto;
import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import com.wora.qatrat7ayat.models.entities.Donor;
import com.wora.qatrat7ayat.models.entities.Hospital;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.models.enumes.BloodType;
import com.wora.qatrat7ayat.repositories.DonorRepository;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.security.repositories.AuthUserRepository;
import com.wora.qatrat7ayat.security.services.IAuthService;
import com.wora.qatrat7ayat.services.INTER.IHospitalService;
import com.wora.qatrat7ayat.services.INTER.IUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DonorServiceTest {

    @Mock
    private DonorRepository donorRepository;
    @Mock
    private IHospitalService hospitalService;
    @Mock
    private DonorMapper donorMapper;
    @Mock
    private IAuthService authService;
    @InjectMocks
    private DonorService sut;
    @Mock
    private SecurityContext securityContext;
    @Mock
    private Authentication authentication;
    @Mock
    private IUserService userService;


    @Test
    @DisplayName("Should create blood donation successfully with authenticated User")
    void createWithAuthenticatedUser() {
        EmbeddedCityDto city = new EmbeddedCityDto(1L, "AGADIR");
        Hospital hospital = new Hospital(1L, "Mhamed 5", List.of());
        EmbeddedHospitalDto embeddedHospitalDto = new EmbeddedHospitalDto(1L, "Mohamed5");
        ProfileDto profileDto = new ProfileDto(1L, "Hamza", "Lamin", null, "0666627661", BloodType.A_MOINS, null, null, city);
        EmbeddedDonorDto donorDto = new EmbeddedDonorDto("Hi yo wsuup Gs", 1L, 1L, "Period ajemy");
        EmbeddedDonorDetailsDto donorDetailsDto = new EmbeddedDonorDetailsDto(1L, "message", embeddedHospitalDto, "Avvv");
        CreateProfileDto createProfileDto = new CreateProfileDto( "Hamza", "Lamin", null, "0666627661", BloodType.A_MOINS.toString(), city.id());

        CreateDonorDto createDonorDto = new CreateDonorDto(
                createProfileDto,
                donorDto
        );

        AuthenticatedUser user = new AuthenticatedUser();
        user.setEmail("test@gmail.com");

        Donor donor = new Donor();
        donor.setId(1L);

        DonorDto expectedDonorDto = new DonorDto(profileDto, donorDetailsDto);

        SecurityContextHolder.setContext(securityContext);
        given(securityContext.getAuthentication()).willReturn(authentication);
        given(authentication.isAuthenticated()).willReturn(true);
        given(authentication.getPrincipal()).willReturn(user);

        given(authService.getUserByEmail(user.getEmail())).willReturn(user);
        given(hospitalService.findHospitalEntity(1L)).willReturn(hospital);
        given(donorMapper.toEntity(donorDto)).willReturn(donor);
        given(donorRepository.save(any(Donor.class))).willReturn(donor);
        given(donorMapper.toDto(donor)).willReturn(expectedDonorDto);

        DonorDto result = sut.create(createDonorDto);
        assertNotNull(result);
        assertSame(expectedDonorDto, result);
        verify(donorRepository).save(any(Donor.class));
    }


    @Test
    @DisplayName("Should create blood donation successfully with unauthenticated User")
    void createWithUnAuthenticatedUser() {
        EmbeddedCityDto city = new EmbeddedCityDto(1L, "AGADIR");
        Hospital hospital = new Hospital(1L, "Mhamed 5", List.of());
        EmbeddedHospitalDto embeddedHospitalDto = new EmbeddedHospitalDto(1L, "Mohamed5");
        ProfileDto profileDto = new ProfileDto(1L, "Hamza", "Lamin", null, "0666627661", BloodType.A_MOINS, null, null, city);
        EmbeddedDonorDto donorDto = new EmbeddedDonorDto("Hi yo wsuup Gs", 1L, 1L, "Period ajemy");
        EmbeddedDonorDetailsDto donorDetailsDto = new EmbeddedDonorDetailsDto(1L, "message", embeddedHospitalDto, "Avvv");
        CreateProfileDto createProfileDto = new CreateProfileDto( "Hamza", "Lamin", null, "0666627661", BloodType.A_MOINS.toString(), city.id());

        CreateDonorDto createDonorDto = new CreateDonorDto(
                createProfileDto,
                donorDto
        );

        User unAuthenticatedUser = new User();
        unAuthenticatedUser.setId(1L);

        Donor donor = new Donor();
        donor.setId(1L);

        DonorDto expectedDonorDto = new DonorDto(profileDto, donorDetailsDto);

        SecurityContextHolder.setContext(securityContext);
        given(securityContext.getAuthentication()).willReturn(authentication);
        given(authentication.isAuthenticated()).willReturn(false);

        given(userService.createUserEntity(createProfileDto)).willReturn(unAuthenticatedUser);
        given(hospitalService.findHospitalEntity(1L)).willReturn(hospital);
        given(donorMapper.toEntity(donorDto)).willReturn(donor);
        given(donorRepository.save(any(Donor.class))).willReturn(donor);
        given(donorMapper.toDto(donor)).willReturn(expectedDonorDto);

        DonorDto result = sut.create(createDonorDto);
        assertNotNull(result);
        assertSame(expectedDonorDto, result);
        verify(donorRepository).save(any(Donor.class));
    }

    @Test
    @DisplayName("Should throw an exception when hospital is not found")
    void createWithHospitalNotFound() {
        EmbeddedCityDto city = new EmbeddedCityDto(1L, "AGADIR");
        ProfileDto profileDto = new ProfileDto(1L, "Hamza", "Lamin", null, "0666627661", BloodType.A_MOINS, null, null, city);
        EmbeddedDonorDto donorDto = new EmbeddedDonorDto("Hi yo wsuup Gs", 1L, 999L, "Period ajemy");
        CreateProfileDto createProfileDto = new CreateProfileDto("Hamza", "Lamin", null, "0666627661", BloodType.A_MOINS.toString(), city.id());

        CreateDonorDto createDonorDto = new CreateDonorDto(
                createProfileDto,
                donorDto
        );

        SecurityContextHolder.setContext(securityContext);
        given(securityContext.getAuthentication()).willReturn(authentication);
        given(authentication.isAuthenticated()).willReturn(false);
        given(hospitalService.findHospitalEntity(999L)).willReturn(null);

        assertThrows(NullPointerException.class, () -> {
            sut.create(createDonorDto);
        });
        verify(hospitalService).findHospitalEntity(999L);
    }

}