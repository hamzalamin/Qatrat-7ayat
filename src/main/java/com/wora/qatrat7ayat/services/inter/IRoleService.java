package com.wora.qatrat7ayat.services.inter;

import com.wora.qatrat7ayat.models.DTOs.Role.CreateRoleDto;
import com.wora.qatrat7ayat.models.DTOs.Role.RoleDto;
import com.wora.qatrat7ayat.models.DTOs.Role.UpdateRoleDto;
import com.wora.qatrat7ayat.services.GenericService;

public interface IRoleService extends GenericService<CreateRoleDto, UpdateRoleDto, RoleDto, Long> {
}
