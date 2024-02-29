package com.example.test;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "OAUTH_CLIENT_DETAILS")
public class OAuthClientDetails extends BaseClientDetails {

    @Id
    @Column(name = "CLIENT_ID", length = 255)
    private String clientId;

    @Column(name = "CLIENT_SECRET", length = 255)
    private String clientSecret;

    @Column(name = "RESOURCE_IDS", length = 255)
    private String resourceIds;

    @Column(name = "SCOPE", length = 255)
    private String scope;

    @Column(name = "AUTHORIZED_GRANT_TYPES", length = 255)
    private String authorizedGrantTypes;

    @Column(name = "WEB_SERVER_REDIRECT_URI", length = 255)
    private String webServerRedirectUri;

    @Column(name = "AUTHORITIES", length = 255)
    private String authorities;

    @Column(name = "ACCESS_TOKEN_VALIDITY")
    private Integer accessTokenValidity;

    @Column(name = "REFRESH_TOKEN_VALIDITY")
    private Integer refreshTokenValidity;

    @Column(name = "ADDITIONAL_INFORMATION", length = 4096)
    private String additionalInformation;

    @Column(name = "AUTOAPPROVE", length = 255)
    private String autoapprove;

    // Additional user-related columns
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USERNAME", length = 255)
    private String username;

    @Column(name = "PASSWORD", length = 255)
    private String password;

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public Set<String> getResourceIds() {
        return super.getResourceIds();
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    @Override
    public Set<String> getScope() {
        return super.getScope();
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return super.getAuthorizedGrantTypes();
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    @Override
    public Map<String,Object> getAdditionalInformation() {
        return super.getAdditionalInformation();
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
