package com.wora.qatrat7ayat.security.repositories;

import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthenticatedUser, Long> {
    Optional<AuthenticatedUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
