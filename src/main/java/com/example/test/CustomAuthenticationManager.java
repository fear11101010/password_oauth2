package com.example.test;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;

import java.util.List;

public class CustomAuthenticationManager extends ProviderManager implements AuthenticationManager {

    public CustomAuthenticationManager(List<AuthenticationProvider> providers) {
        super(providers);
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        // Implement custom authentication logic here
        return super.authenticate(authentication);
    }
}
