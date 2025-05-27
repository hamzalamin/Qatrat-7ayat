package com.wora.qatrat7ayat.security.repositories;

import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthenticatedUser, Long> {
    Optional<AuthenticatedUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
