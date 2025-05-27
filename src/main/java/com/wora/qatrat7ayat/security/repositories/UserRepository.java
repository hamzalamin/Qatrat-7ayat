package com.wora.qatrat7ayat.security.repositories;

import com.wora.qatrat7ayat.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
