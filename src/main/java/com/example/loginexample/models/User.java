package com.example.loginexample.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="user_table", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {

    @Id
    @GeneratedValue
    Integer id;
    @Basic
    @Column(nullable = false)
    String username;
    @Column(nullable = false)
    String lastname;
    String firstname;
    String country;
    String password;
    @Enumerated(EnumType.STRING)
    ERole role;

    // Este método retorna la colección de roles/granted authorities del usuario.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    // Este método retorna la contraseña del usuario.
    @Override
    public String getPassword() {
        return password;
    }

    // Este método retorna el nombre de usuario del usuario.
    @Override
    public String getUsername() {
        return username;
    }

    // Métodos relacionados con la expiración de la cuenta, bloqueo, etc.

    @Override
    public boolean isAccountNonExpired() {
        return true;  // La cuenta nunca expira.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // La cuenta nunca está bloqueada.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Las credenciales nunca expiran.
    }

    @Override
    public boolean isEnabled() {
        return true;  // La cuenta siempre está habilitada.
    }


}
