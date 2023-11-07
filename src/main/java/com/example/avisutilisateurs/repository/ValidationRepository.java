package com.example.avisutilisateurs.repository;

import com.example.avisutilisateurs.entites.Validation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation, Integer> {
    Optional<Validation> findByCode(String code);
}
