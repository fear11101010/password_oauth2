package com.example.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthClientDetailRepository extends JpaRepository<OAuthClientDetails,String> {

    OAuthClientDetails findByClientId(String clientId);

    OAuthClientDetails findByUserId(Long userId);

    OAuthClientDetails findByUsername(String username);
}
