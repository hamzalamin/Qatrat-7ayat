package com.wora.qatrat7ayat.seeders;

import com.wora.qatrat7ayat.security.models.entities.AuthenticatedUser;
import com.wora.qatrat7ayat.security.models.entities.Role;
import com.wora.qatrat7ayat.security.repositories.AuthUserRepository;
import com.wora.qatrat7ayat.security.services.IRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(2)
@Component
@RequiredArgsConstructor
@Slf4j
public class AdminSeeder implements CommandLineRunner {
    private final AuthUserRepository authUserRepository;
    private final IRoleService roleService;

    @Override
    public void run(String... args) {
        seedAdmin();
    }

    private void seedAdmin() {
        Role admin = roleService.findRoleByName("ROLE_ADMIN");
        Role coordinator = roleService.findRoleByName("ROLE_COORDINATOR");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin";
        String hashedPassword = encoder.encode(rawPassword);
        if (authUserRepository.count() == 0) {
            List<AuthenticatedUser> auths = List.of(
                    new AuthenticatedUser("admin.qatrat@gmail.com", hashedPassword, admin),
                    new AuthenticatedUser("cordinator.qatrat@gmail.com", hashedPassword, coordinator)

            );
            authUserRepository.saveAll(auths);
            log.info("Admin saved successfully");
        } else {
            log.info("Admin already exist, skipping seeding.");
        }
    }
}
