package com.agence.users;

import com.agence.users.repos.AppUserRepository;
import com.agence.users.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgenceUsersBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgenceUsersBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner init(AccountService accountService, AppUserRepository appUserRepository) {
        return args -> {
            if (appUserRepository.count() > 0) {
                return;
            }
            accountService.saveNewRole("ADMIN");
            accountService.saveNewRole("USER");

            accountService.saveNewUser("admin", "1234", "1234");
            accountService.saveNewUser("user1", "1234", "1234");

            accountService.addRoleToUser("admin", "ADMIN");
            accountService.addRoleToUser("user1", "USER");
        };
    }
}
