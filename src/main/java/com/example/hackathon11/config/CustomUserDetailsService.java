package com.example.hackathon11.config;

import com.example.hackathon11.entity.User;
import com.example.hackathon11.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow
                (() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails
                .User(user.getUsername(), user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        // Singleton потому что роль может быть только 1
    }

    private Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
