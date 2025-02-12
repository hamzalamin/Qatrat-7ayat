package com.wora.qatrat7ayat.security.repositories;

import com.wora.qatrat7ayat.security.models.Role;
import com.wora.qatrat7ayat.security.models.enume.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}