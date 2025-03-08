package com.fitnesscenter.membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword())); // Encrypt password
        memberRepository.save(member);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Optional<Member> user = memberRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return "Login successful!";
        } else {
            return "Invalid username or password.";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "Please send a POST request with username & password.";
    }

}
