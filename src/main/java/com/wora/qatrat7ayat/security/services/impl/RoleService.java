package com.wora.qatrat7ayat.security.services.impl;

import com.wora.qatrat7ayat.exceptions.DefaultEntityNotFound;
import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.exceptions.RoleLinkedToUsersException;
import com.wora.qatrat7ayat.mappers.RoleMapper;
import com.wora.qatrat7ayat.security.DTO.Role.CreateRoleDto;
import com.wora.qatrat7ayat.security.DTO.Role.RoleDto;
import com.wora.qatrat7ayat.security.DTO.Role.UpdateRoleDto;
import com.wora.qatrat7ayat.security.models.Role;
import com.wora.qatrat7ayat.security.repositories.RoleRepository;
import com.wora.qatrat7ayat.security.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new DefaultEntityNotFound("Default role "+ name +" not found"));
    }

    @Override
    public Role findRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role", id));
    }

    @Override
    public Page<RoleDto> findAllPage(Integer pageNumber, Integer size) {
        PageRequest pageable = PageRequest.of(pageNumber, size);
        return roleRepository.findAll(pageable).map(roleMapper::toDto);
    }

    @Override
    public RoleDto create(CreateRoleDto createRoleDto) {
        System.out.println(createRoleDto);
        Role role = roleMapper.toEntity(createRoleDto);
        Role createdRole = roleRepository.save(role);
        return roleMapper.toDto(createdRole);
    }

    @Override
    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role", id));
        return roleMapper.toDto(role);
    }

    @Override
    public RoleDto update(UpdateRoleDto updateRoleDto, Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role", id));
        if (role.getUsers() != null && !role.getUsers().isEmpty()) {
            throw new RoleLinkedToUsersException(role.getId());
        }
        role.setName(updateRoleDto.name());
        Role updatedRole = roleRepository.save(role);
        return roleMapper.toDto(updatedRole);
    }

    @Override
    public List<RoleDto> findAll(Integer pageNumber, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return roleRepository.findAll(pageRequest).stream()
                .map(roleMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role", id));
        if (role.getUsers() != null && !role.getUsers().isEmpty()) {
            throw new RoleLinkedToUsersException(role.getId());
        }
        roleRepository.delete(role);
    }
}
