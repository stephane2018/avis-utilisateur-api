package com.example.avisutilisateurs.repository;

import com.example.avisutilisateurs.entites.Avis;
import org.springframework.data.repository.CrudRepository;

public interface AvisRepository extends CrudRepository<Avis, Integer> {
}
