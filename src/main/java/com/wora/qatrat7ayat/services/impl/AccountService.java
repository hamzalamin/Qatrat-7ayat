package com.wora.qatrat7ayat.services.impl;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.exceptions.UserAlreadyExist;
import com.wora.qatrat7ayat.mappers.AccountMapper;
import com.wora.qatrat7ayat.models.DTOs.account.AccountDto;
import com.wora.qatrat7ayat.models.DTOs.account.CreateUserAccountDto;
import com.wora.qatrat7ayat.models.DTOs.account.UpdateUserAccountDto;
import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.security.models.dtos.SignupResponse;
import com.wora.qatrat7ayat.security.mappers.AuthMapper;
import com.wora.qatrat7ayat.security.models.entities.AuthenticatedUser;
import com.wora.qatrat7ayat.security.models.entities.Role;
import com.wora.qatrat7ayat.security.repositories.UserRepository;
import com.wora.qatrat7ayat.security.services.IAuthService;
import com.wora.qatrat7ayat.security.services.IRoleService;
import com.wora.qatrat7ayat.services.inter.IAccountService;
import com.wora.qatrat7ayat.services.inter.ICityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {
    private final UserRepository userRepository;
    private final IAuthService authService;
    private final IRoleService roleService;
    private final ICityService cityService;
    private final AuthMapper authMapper;
    private final AccountMapper accountMapper;

    @Override
    public boolean toggleSuspension(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));

        user.setSuspended(!user.isSuspended());
        userRepository.save(user);

        return user.isSuspended();
    }

    @Override
    public SignupResponse create(CreateUserAccountDto createUserAccountDto) {
        if (authService.existsByEmail(createUserAccountDto.getEmail())) {
            throw new UserAlreadyExist(createUserAccountDto.getEmail());
        }
        City city = cityService.findCityEntity(createUserAccountDto.getCityId());
        Role role = roleService.findRoleById(createUserAccountDto.getRoleId());
        AuthenticatedUser user = authMapper.toEntity(createUserAccountDto);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(createUserAccountDto.getPassword());
        user.setPassword(hashedPassword);
        user.setRole(role);
        user.setCity(city);
        user.setSuspended(true);
        AuthenticatedUser savedUser = userRepository.save(user);
        return authMapper.toDto(savedUser);
    }

    @Override
    public SignupResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));

        return authMapper.toDto((AuthenticatedUser) user);
    }

    @Override
    public SignupResponse update(UpdateUserAccountDto updateUserAccountDto, Long id) {
        AuthenticatedUser existingUser = findUserById(id);
        validateEmailUpdate(existingUser, updateUserAccountDto.getEmail());
        updateUserRole(existingUser, updateUserAccountDto.getRoleId());
        updateUserBasicInfo(existingUser, updateUserAccountDto);
        updateUserCity(existingUser, updateUserAccountDto.getCityId());

        AuthenticatedUser updatedUser = userRepository.save(existingUser);
        return authMapper.toDto(updatedUser);
    }

    private AuthenticatedUser findUserById(Long id) {
        return (AuthenticatedUser) userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User Account", id));
    }

    private void validateEmailUpdate(AuthenticatedUser existingUser, String newEmail) {
        if (!existingUser.getEmail().equals(newEmail) && authService.existsByEmail(newEmail)) {
            throw new UserAlreadyExist(newEmail);
        }
    }

    private void updateUserRole(AuthenticatedUser user, Long roleId) {
        if (roleId != null) {
            Role role = roleService.findRoleById(roleId);
            user.setRole(role);
        }
    }

    private void updateUserBasicInfo(AuthenticatedUser user, UpdateUserAccountDto dto) {
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBloodType(dto.getBloodType());
        user.setPhone(dto.getPhone());
        user.setGender(dto.getGender());
    }

    private void updateUserCity(AuthenticatedUser user, Long cityId) {
        if (cityId != null) {
            City city = cityService.findCityEntity(cityId);
            user.setCity(city);
        }
    }

    @Override
    public List<SignupResponse> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public Page<AccountDto> findAllPage(Integer pageNumber, Integer size) {
        PageRequest pageable = PageRequest.of(pageNumber, size);

        Page<User> userPage = userRepository.findAll(pageable);
        int offset = 1;
        List<AccountDto> signupResponses = userPage.getContent()
                .stream()
                .skip(offset)
                .map(accountMapper::userToAccountDto)
                .collect(Collectors.toList());

        return new PageImpl<>(signupResponses, pageable, userPage.getTotalElements());

//        return userRepository.findAll(pageable).map(accountMapper::userToAccountDto);

    }

    @Override
    public void delete(Long id) {
        userRepository.delete(findUserById(id));
    }
}
