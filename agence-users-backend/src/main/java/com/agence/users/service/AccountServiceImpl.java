package com.agence.users.service;

import com.agence.users.entities.AppRole;
import com.agence.users.entities.AppUser;
import com.agence.users.repos.AppRoleRepository;
import com.agence.users.repos.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String confirmedPassword) {
        if (!password.equals(confirmedPassword)) {
            throw new RuntimeException("Passwords don't match");
        }
        AppUser existing = appUserRepository.findByUsername(username);
        if (existing != null) {
            throw new RuntimeException("User already exists");
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setActive(true);
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole saveNewRole(String roleName) {
        AppRole existing = appRoleRepository.findByRoleName(roleName);
        if (existing != null) {
            return existing;
        }
        AppRole role = new AppRole();
        role.setRoleName(roleName);
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appUser != null && appRole != null) {
            appUser.getRoles().add(appRole);
            appUserRepository.save(appUser);
        }
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppUser saveNewUserWithEmail(String username, String password, String email, String verificationCode) {
        AppUser existing = appUserRepository.findByUsername(username);
        if (existing != null) {
            throw new RuntimeException("User already exists");
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setEmail(email);
        appUser.setVerificationCode(verificationCode);
        appUser.setActive(false);
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser activateUser(AppUser user) {
        user.setActive(true);
        return appUserRepository.save(user);
    }
}
