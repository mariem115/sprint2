package com.agence.users.bootstrap;

import com.agence.users.entities.AppUser;
import com.agence.users.repos.AppUserRepository;
import com.agence.users.service.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserBootstrap implements CommandLineRunner {

    private final AccountService accountService;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.bootstrap.admin.username:admin}")
    private String adminUsername;

    @Value("${app.bootstrap.admin.password:changeme}")
    private String adminPassword;

    public AdminUserBootstrap(AccountService accountService,
                              AppUserRepository appUserRepository,
                              PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        accountService.saveNewRole("USER");
        accountService.saveNewRole("ADMIN");

        AppUser existing = accountService.loadUserByUsername(adminUsername);
        if (existing == null) {
            accountService.saveNewUser(adminUsername, adminPassword, adminPassword);
            accountService.addRoleToUser(adminUsername, "ADMIN");
            System.out.println("[BOOTSTRAP] Admin created: " + adminUsername + " / " + adminPassword);
        } else {
            existing.setPassword(passwordEncoder.encode(adminPassword));
            existing.setActive(true);
            appUserRepository.save(existing);
            if (existing.getRoles().stream().noneMatch(r -> "ADMIN".equals(r.getRoleName()))) {
                accountService.addRoleToUser(adminUsername, "ADMIN");
            }
            System.out.println("[BOOTSTRAP] Admin reset: " + adminUsername + " / " + adminPassword);
        }
    }
}
