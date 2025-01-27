package com.wora.qatrat7ayat.repositories;

import com.wora.qatrat7ayat.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<User, Long> {
}
