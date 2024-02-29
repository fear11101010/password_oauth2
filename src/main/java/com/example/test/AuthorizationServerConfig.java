package com.example.test;

import com.example.test.jwt.CustomTokenEnhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
@EnableResourceServer
//@Order(2)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final JdbcClientDetailService jdbcClientDetailService;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthorizationServerConfig(JdbcClientDetailService jdbcClientDetailService, CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.jdbcClientDetailService = jdbcClientDetailService;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .withClientDetails(jdbcClientDetailService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChains = new TokenEnhancerChain();
        tokenEnhancerChains.setTokenEnhancers(Arrays.asList(customTokenEnhancer(),jwtAccessTokenConverter()));
        endpoints
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChains)
                .authenticationManager(new CustomAuthenticationManager(Arrays.asList(authenticationProvider())));
    }


    @Bean
    public TokenStore tokenStore() throws NoSuchAlgorithmException {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() throws NoSuchAlgorithmException {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(generateKeyPair());
        return converter;
    }

    @Bean
    public CustomTokenEnhancer customTokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        String algorithm = "RSA";

        // Generate a key pair generator for the specified algorithm
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);

        // Set the key size (e.g., 2048 bits)
        keyPairGenerator.initialize(2048);

        // Generate the key pair
        return keyPairGenerator.generateKeyPair();

    }
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        return daoAuthenticationProvider;
    }
}
