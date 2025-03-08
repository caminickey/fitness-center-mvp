package com.fitnesscenter.membership;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("admin"); // Encrypt "admin"
        System.out.println("Encrypted Password: " + encodedPassword);
    }
}
