package com.wora.qatrat7ayat.seeders;

import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.security.models.Role;
import com.wora.qatrat7ayat.security.repositories.AuthUserRepository;
import com.wora.qatrat7ayat.security.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin";
        String hashedPassword = encoder.encode(rawPassword);
        if (authUserRepository.count() == 0) {
            List<AuthenticatedUser> auths = List.of(
                    new AuthenticatedUser("admin.qatrat@gmail.com", hashedPassword, admin)
            );
            authUserRepository.saveAll(auths);
            System.out.println("Admin saved successfully");
        } else {
            System.out.println("Admin already exist, skipping seeding.");
        }
    }
}
