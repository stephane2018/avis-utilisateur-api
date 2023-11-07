package com.example.avisutilisateurs.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "utilisateur")
public class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;
    @Column(name="motDePasse")
    private String motDePasse;

    private String nom;
    private String email;
    private boolean actif = false;
    @OneToOne(cascade = CascadeType.ALL)
    private  Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE"+this.role.getLibelle()));
    }

    @Override
    public String getPassword() {
        return this.motDePasse;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isEnabled() {
        return this.actif;
    }
}
