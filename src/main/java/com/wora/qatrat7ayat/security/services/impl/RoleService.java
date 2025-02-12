package com.wora.qatrat7ayat.security.services.impl;

import com.wora.qatrat7ayat.exceptions.DefaultEntityNotFound;
import com.wora.qatrat7ayat.security.models.Role;
import com.wora.qatrat7ayat.security.repositories.RoleRepository;
import com.wora.qatrat7ayat.security.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new DefaultEntityNotFound("Default role ROLE_USER not found"));
    }
}
