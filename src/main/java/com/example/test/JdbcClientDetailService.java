package com.example.test;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JdbcClientDetailService implements ClientDetailsService {
    private final OAuthClientDetailRepository oAuthClientDetailRepository;

    public JdbcClientDetailService(OAuthClientDetailRepository oAuthClientDetailRepository) {
        this.oAuthClientDetailRepository = oAuthClientDetailRepository;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OAuthClientDetails oAuthClientDetails = oAuthClientDetailRepository.findByClientId(clientId);
        if (Objects.isNull(oAuthClientDetails)){
            throw new ClientRegistrationException("Client not found with id : "+clientId);
        }
        return oAuthClientDetails;
    }
}
