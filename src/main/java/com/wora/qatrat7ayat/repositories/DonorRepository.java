package com.wora.qatrat7ayat.repositories;

import com.wora.qatrat7ayat.models.entities.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
}
