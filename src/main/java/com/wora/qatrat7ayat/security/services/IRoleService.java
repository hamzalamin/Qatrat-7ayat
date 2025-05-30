package com.wora.qatrat7ayat.security.services;

import com.wora.qatrat7ayat.security.models.dtos.Role.CreateRoleDto;
import com.wora.qatrat7ayat.security.models.dtos.Role.RoleDto;
import com.wora.qatrat7ayat.security.models.dtos.Role.UpdateRoleDto;
import com.wora.qatrat7ayat.security.models.entities.Role;
import com.wora.qatrat7ayat.services.GenericService;
import org.springframework.data.domain.Page;

public interface IRoleService extends GenericService<CreateRoleDto, UpdateRoleDto, RoleDto, Long> {
    Role findRoleByName(String name);
    Role findRoleById(Long id);
    Page<RoleDto> findAllPage(Integer pageNumber, Integer size);
}
