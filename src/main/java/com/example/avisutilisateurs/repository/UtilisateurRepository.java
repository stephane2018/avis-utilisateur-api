package com.example.avisutilisateurs.repository;

import com.example.avisutilisateurs.entites.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UtilisateurRepository  extends CrudRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByEmail(String email);
}
