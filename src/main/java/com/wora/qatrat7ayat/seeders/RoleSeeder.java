package com.wora.qatrat7ayat.seeders;

import com.wora.qatrat7ayat.security.models.Role;
import com.wora.qatrat7ayat.security.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(1)
@Component
@RequiredArgsConstructor
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
            System.out.println("roles saved successfully");
        } else {
            System.out.println("Roles already exist, skipping seeding.");
        }
    }
}
