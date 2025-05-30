package com.wora.qatrat7ayat.security.repositories;

import com.wora.qatrat7ayat.security.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}