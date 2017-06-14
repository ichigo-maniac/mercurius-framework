package org.mercuriusframework.security;

import org.mercuriusframework.entities.CustomerEntity;
import org.mercuriusframework.enums.AuthenticationType;
import org.mercuriusframework.enums.SocialNetworkType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Customer user details
 */
public class CustomerUserDetails implements UserDetails {

    /**
     * Username (code)
     */
    private String username;

    /**
     * Password
     */
    private String password;

    /**
     * Authentication type
     */
    private AuthenticationType authenticationType;

    /**
     * Social network type
     */
    private SocialNetworkType socialNetworkType;

    /**
     * Social network id
     */
    private String socialNetworkId;

    /**
     * Constructor
     * @param customerEntity Customer entity
     */
    public CustomerUserDetails(CustomerEntity customerEntity) {
        this.username = customerEntity.getCode();
        this.password = customerEntity.getPassword();
        this.authenticationType = customerEntity.getAuthenticationType();
        this.socialNetworkId = customerEntity.getSocialNetworkId();
        this.socialNetworkType = customerEntity.getSocialNetworkType();
    }

    /**
     * Get authorities
     * @return Authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    /**
     * Get password
     * @return Password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Get username
     * @return Username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Is user account not expired
     * @return Is user account not expired check result
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Is user account not locked
     * @return Is user account not locked check result
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Is credential not expired
     * @return Is credential not expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Is user account enabled
     * @return Is user account enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Get authentication type
     * @return Authentication type
     */
    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    /**
     * Get social network type
     * @return Social network type
     */
    public SocialNetworkType getSocialNetworkType() {
        return socialNetworkType;
    }

    /**
     * Get social network id
     * @return Social network id
     */
    public String getSocialNetworkId() {
        return socialNetworkId;
    }

}
