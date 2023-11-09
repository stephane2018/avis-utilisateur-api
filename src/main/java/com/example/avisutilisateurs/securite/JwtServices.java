package com.example.avisutilisateurs.securite;

import com.example.avisutilisateurs.entites.Utilisateur;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class JwtServices {
    UtilisateurService utilisateurService;
    public Map<String, String> genrate(String username){
        Utilisateur utlisateur=this.utilisateurService.loadUserByUsername(username);
        return this.genrateJwt(utlisateur);
    }

    private Map<String, String> genrateJwt(Utilisateur utilisateur){
        return null;
    }
}
