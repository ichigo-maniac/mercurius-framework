package org.mercuriusframework.security;

import org.mercuriusframework.entities.RoleEntity;
import org.mercuriusframework.entities.EmployeeEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Employee user details
 */
public class EmployeeUserDetails implements UserDetails {

    /**
     * Username (code)
     */
    private String username;

    /**
     * Password
     */
    private String password;

    /**
     * Authorities
     */
    private List<SimpleGrantedAuthority> grantedAuthorities;

    /**
     * Constructor
     * @param employeeEntity Employee entity instance
     */
    public EmployeeUserDetails(EmployeeEntity employeeEntity) {
        this.username = employeeEntity.getCode();
        this.password = employeeEntity.getPassword();
        /** Create authorities from roles */
        this.grantedAuthorities = new ArrayList<>(employeeEntity.getRoles().size());
        grantedAuthorities.addAll(employeeEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toList()));
    }

    /**
     * Get authorities
     * @return Authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
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
}
