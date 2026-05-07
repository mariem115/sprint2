package com.agence.users.security;

import com.agence.users.entities.AppUser;
import com.agence.users.repos.AppUserRepository;
import com.agence.users.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(usernameOrEmail);
        if (appUser == null && usernameOrEmail != null && usernameOrEmail.contains("@")) {
            appUser = appUserRepository.findByEmail(usernameOrEmail);
        }
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found: " + usernameOrEmail);
        }
        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .disabled(!appUser.isActive())
                .authorities(appUser.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getRoleName()))
                        .collect(Collectors.toList()))
                .build();
    }
}
