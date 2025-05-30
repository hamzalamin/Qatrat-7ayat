package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.security.models.dtos.Role.CreateRoleDto;
import com.wora.qatrat7ayat.security.models.dtos.Role.RoleDto;
import com.wora.qatrat7ayat.security.models.dtos.Role.UpdateRoleDto;
import com.wora.qatrat7ayat.security.models.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<Role, RoleDto> {
    Role toEntity(RoleDto dto);
    Role toEntity(CreateRoleDto dto);
    Role toEntity(UpdateRoleDto dto);
    RoleDto toDto(Role role);
}
