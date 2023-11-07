package com.example.avisutilisateurs.securite;

import com.example.avisutilisateurs.entites.Role;
import com.example.avisutilisateurs.entites.Utilisateur;
import com.example.avisutilisateurs.entites.Validation;
import com.example.avisutilisateurs.repository.UtilisateurRepository;
import com.example.avisutilisateurs.repository.ValidationRepository;
import com.example.avisutilisateurs.service.NotificationService;
import com.example.avisutilisateurs.service.TypeDeRole;
import com.example.avisutilisateurs.service.ValidationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;

    public void inscription(Utilisateur utilisateur) {
        if (!utilisateur.getEmail().contains("@")) {
            throw new RuntimeException("votre mail est invalide");
        }
        if (!utilisateur.getEmail().contains(".")) {
            throw new RuntimeException("votre doit contenir un .");
        }

        Optional<Utilisateur> utilisateurOptional = this.utilisateurRepository.findByEmail(utilisateur.getEmail());
        if (utilisateurOptional.isPresent()) {
            throw new RuntimeException("ce user exist deja ");
        }
        Role roleUser = new Role();
        roleUser.setLibelle(TypeDeRole.UTILISATEUR);

        String motdepassCrypter = this.passwordEncoder.encode((utilisateur.getPassword()));
        utilisateur.setMotDePasse(motdepassCrypter);
        utilisateur.setRole(roleUser);
        utilisateur = this.utilisateurRepository.save(utilisateur);
        this.validationService.enregsiter(utilisateur);

    }

    public void activation(Map<String, String> activation) {
        Validation validation = this.validationService.lireEnfontionDuCode(activation.get("code"));

        if (Instant.now().isAfter(validation.getExpire())) {
            throw new RuntimeException("votre code a expirer");
        }

        Utilisateur UtilsateurActiver = this.utilisateurRepository.findById((validation.getUtilisateur().getId())).orElseThrow(() -> new RuntimeException("utilisateur inconu"));
        UtilsateurActiver.setActif(true);
        this.utilisateurRepository.save(UtilsateurActiver);
    }
}


