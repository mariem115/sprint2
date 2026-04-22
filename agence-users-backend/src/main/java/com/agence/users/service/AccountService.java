package com.agence.users.service;

import com.agence.users.entities.AppRole;
import com.agence.users.entities.AppUser;

public interface AccountService {

    AppUser saveNewUser(String username, String password, String confirmedPassword);

    AppUser saveNewUserWithEmail(String username, String password, String email, String verificationCode);

    AppRole saveNewRole(String roleName);

    void addRoleToUser(String username, String roleName);

    AppUser loadUserByUsername(String username);

    AppUser activateUser(AppUser user);
}
