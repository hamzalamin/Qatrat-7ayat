package com.wora.qatrat7ayat.security.mappers;

import com.wora.qatrat7ayat.security.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.security.models.DTOs.roleDtos.CreateRoleDto;
import com.wora.qatrat7ayat.security.models.DTOs.roleDtos.RoleDto;
import com.wora.qatrat7ayat.security.models.DTOs.roleDtos.UpdateRoleDto;
import com.wora.qatrat7ayat.security.models.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<Role, RoleDto> {
    Role toEntity(RoleDto dto);
    Role toEntity(CreateRoleDto dto);
    Role toEntity(UpdateRoleDto dto);
    RoleDto toDto(Role role);
}
