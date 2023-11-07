package com.example.avisutilisateurs.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "validation")
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Instant creation;
    private Instant expire;
    private Instant activation;
    private String code;
    @OneToOne(cascade = CascadeType.ALL)
    private Utilisateur utilisateur;
}
