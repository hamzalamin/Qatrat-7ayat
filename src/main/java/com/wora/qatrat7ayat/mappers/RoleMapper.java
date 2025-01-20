package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.models.DTOs.Role.CreateRoleDto;
import com.wora.qatrat7ayat.models.DTOs.Role.RoleDto;
import com.wora.qatrat7ayat.models.DTOs.Role.UpdateRoleDto;
import com.wora.qatrat7ayat.models.entities.Role;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<Role, RoleDto> {
    Role toEntity(CreateRoleDto dto);
    Role toEntity(UpdateRoleDto dto);
    Role toEntity(RoleDto dto);
    RoleDto toDto(Role role);
}
