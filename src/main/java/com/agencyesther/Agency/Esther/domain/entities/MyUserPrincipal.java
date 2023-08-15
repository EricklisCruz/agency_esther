package com.agencyesther.Agency.Esther.domain.entities;

import com.agencyesther.Agency.Esther.domain.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

/**
 * @author Ericklis
 */

public record MyUserPrincipal(User user) implements UserDetails {

    @Serial
    private static final long serialVersionUID = 6043457173208758465L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.user.getUserRole() == UserRole.ADMIN_ROLE) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPasswordd();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
