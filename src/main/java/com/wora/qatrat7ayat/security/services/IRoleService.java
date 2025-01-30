package com.wora.qatrat7ayat.security.services;

import com.wora.qatrat7ayat.security.models.Role;

public interface IRoleService {
    Role findRoleByName(String name);
}
