package org.novak.java.facade;

import org.novak.java.auth.JwtTokenProvider;
import org.novak.java.model.user.User;
import org.novak.java.repository.UserRepository;
import org.novak.java.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthFacade {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserSessionService sessionService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthFacade(UserRepository userRepository,
                      UserSessionService sessionService,
                      PasswordEncoder passwordEncoder,
                      JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            sessionService.setActiveUser(user);
            return jwtTokenProvider.generateToken(user.getUsername(), user.getUserType().name());
        } else {
            throw new RuntimeException("Invalid credentials!");
        }
    }
}
