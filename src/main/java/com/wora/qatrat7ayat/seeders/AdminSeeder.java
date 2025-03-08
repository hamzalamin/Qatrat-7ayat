package com.wora.qatrat7ayat.seeders;

import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.security.models.Role;
import com.wora.qatrat7ayat.security.repositories.AuthUserRepository;
import com.wora.qatrat7ayat.security.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(2)
@Component
@RequiredArgsConstructor
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
            System.out.println("Admin saved successfully");
        } else {
            System.out.println("Admin already exist, skipping seeding.");
        }
    }
}
