package com.agence.users.controllers;

import com.agence.users.entities.AppUser;
import com.agence.users.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AccountService accountService;

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser user) {
        String code = String.valueOf((int) (Math.random() * 900000) + 100000);

        AppUser savedUser = accountService.saveNewUserWithEmail(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                code
        );

        accountService.addRoleToUser(user.getUsername(), "USER");

        sendVerificationEmail(savedUser.getEmail(), code);

        savedUser.setPassword(null);
        return savedUser;
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam String username, @RequestParam String code) {
        AppUser user = accountService.loadUserByUsername(username);
        if (user == null) {
            return "User not found";
        }
        if (user.getVerificationCode() != null && user.getVerificationCode().equals(code)) {
            accountService.activateUser(user);
            return "Email verified successfully";
        }
        return "Invalid code";
    }

    private void sendVerificationEmail(String to, String code) {
        if (mailSender == null || to == null || to.isBlank()) {
            System.out.println("[DEV] Verification code for " + to + ": " + code);
            return;
        }
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setSubject("Agence de Voyage — Vérification d'email");
            msg.setText("Votre code de vérification est : " + code);
            mailSender.send(msg);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
            System.out.println("[DEV] Verification code for " + to + ": " + code);
        }
    }
}
