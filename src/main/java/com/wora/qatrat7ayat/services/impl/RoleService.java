package com.wora.qatrat7ayat.services.impl;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.mappers.RoleMapper;
import com.wora.qatrat7ayat.models.DTOs.Role.CreateRoleDto;
import com.wora.qatrat7ayat.models.DTOs.Role.RoleDto;
import com.wora.qatrat7ayat.models.DTOs.Role.UpdateRoleDto;
import com.wora.qatrat7ayat.models.entities.Role;
import com.wora.qatrat7ayat.repositories.RoleRepository;
import com.wora.qatrat7ayat.services.inter.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleDto create(CreateRoleDto createRoleDto) {
        Role role = roleMapper.toEntity(createRoleDto);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toDto(savedRole);
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
        role.setName(updateRoleDto.name());
        Role updatedRole = roleRepository.save(role);
        return roleMapper.toDto(updatedRole);
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role", id));
        roleRepository.delete(role);
    }
}
