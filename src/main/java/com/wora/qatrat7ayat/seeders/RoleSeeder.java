package com.wora.qatrat7ayat.seeders;

import com.wora.qatrat7ayat.security.models.entities.Role;
import com.wora.qatrat7ayat.security.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(1)
@Component
@RequiredArgsConstructor
@Slf4j
public class RoleSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        seedRole();
    }

    private void seedRole() {
        if (roleRepository.count() == 0) {
            List<Role> roles = List.of(
                    new Role( "ROLE_USER"),
                    new Role("ROLE_ADMIN"),
                    new Role("ROLE_COORDINATOR")
            );
            roleRepository.saveAll(roles);
            log.info("Roles seeded with success");
        } else {
            log.info("Roles already exist, skipping seeding.");
        }
    }
}
