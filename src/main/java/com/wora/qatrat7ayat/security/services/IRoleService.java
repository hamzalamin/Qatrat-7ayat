package com.wora.qatrat7ayat.security.services;

import com.wora.qatrat7ayat.security.DTO.Role.CreateRoleDto;
import com.wora.qatrat7ayat.security.DTO.Role.RoleDto;
import com.wora.qatrat7ayat.security.DTO.Role.UpdateRoleDto;
import com.wora.qatrat7ayat.security.models.Role;
import com.wora.qatrat7ayat.services.GenericService;

public interface IRoleService extends GenericService<CreateRoleDto, UpdateRoleDto, RoleDto, Long> {
    Role findRoleByName(String name);
    Role findRoleById(Long id);
}
