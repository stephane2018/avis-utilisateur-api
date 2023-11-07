package com.example.avisutilisateurs.service;

import com.example.avisutilisateurs.entites.Avis;
import com.example.avisutilisateurs.repository.AvisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AvisService {
    private final AvisRepository avisRepository;
    public void creer(Avis avis){
        this.avisRepository.save(avis);
    }
}
