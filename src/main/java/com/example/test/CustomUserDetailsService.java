package com.example.test;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final OAuthClientDetailRepository oAuthClientDetailRepository;

    public CustomUserDetailsService(OAuthClientDetailRepository oAuthClientDetailRepository) {
        this.oAuthClientDetailRepository = oAuthClientDetailRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OAuthClientDetails user = oAuthClientDetailRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_ADMIN")
                .build();
    }
}
