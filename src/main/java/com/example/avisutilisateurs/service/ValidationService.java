package com.example.avisutilisateurs.service;

import com.example.avisutilisateurs.entites.Utilisateur;
import com.example.avisutilisateurs.entites.Validation;
import com.example.avisutilisateurs.repository.ValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
@AllArgsConstructor
public class ValidationService {
    private ValidationRepository validationRepository;
    private NotificationService notificationService;

    public void enregsiter(Utilisateur utilisateur){
        Validation validation = new Validation();
        validation.setUtilisateur(utilisateur);
        Instant creation= Instant.now();
        validation.setCreation(creation);
        Instant expiration= creation.plus(10, ChronoUnit.MINUTES);
        validation.setExpire(expiration);
        Random random =new Random();
        int randomInteger= random.nextInt(999999);
        String code = String.format("%06d", randomInteger);
        validation.setCode(code);
        this.validationRepository.save(validation);
        this.notificationService.envoyer(validation);

    }
    public Validation lireEnfontionDuCode(String code){
      return   this.validationRepository.findByCode(code).orElseThrow(()-> new RuntimeException("Votre code est invalide "));
    }
}
