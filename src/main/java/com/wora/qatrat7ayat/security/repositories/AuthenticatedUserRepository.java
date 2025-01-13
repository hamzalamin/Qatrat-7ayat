package com.wora.qatrat7ayat.security.repositories;

import com.wora.qatrat7ayat.security.models.entities.AuthenticatedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticatedUserRepository extends JpaRepository<AuthenticatedUser, Long> {
    AuthenticatedUser findByEmail(String email);
}