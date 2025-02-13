package com.wora.qatrat7ayat.repositories;

import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<AuthenticatedUser, Long> {
}
