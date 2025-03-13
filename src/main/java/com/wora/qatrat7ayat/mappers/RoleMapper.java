package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.security.DTO.Role.CreateRoleDto;
import com.wora.qatrat7ayat.security.DTO.Role.RoleDto;
import com.wora.qatrat7ayat.security.DTO.Role.UpdateRoleDto;
import com.wora.qatrat7ayat.security.models.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<Role, RoleDto> {
    Role toEntity(RoleDto dto);
    Role toEntity(CreateRoleDto dto);
    Role toEntity(UpdateRoleDto dto);
    RoleDto toDto(Role role);
}
