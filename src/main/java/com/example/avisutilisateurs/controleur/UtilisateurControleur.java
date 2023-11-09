package com.example.avisutilisateurs.controleur;

import com.example.avisutilisateurs.dto.AuthentificationDTO;
import com.example.avisutilisateurs.entites.Utilisateur;
import com.example.avisutilisateurs.securite.JwtServices;
import com.example.avisutilisateurs.securite.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
@RestController
@Slf4j
@AllArgsConstructor
public class UtilisateurControleur {
    private AuthenticationManager authenticationManager;
    private  UtilisateurService utilisateurService;
    private JwtServices jwtServices;
    @PostMapping(path = "inscription")
    public  void inscription(@RequestBody Utilisateur utilisateur){
        log.info("inscription");
        this.utilisateurService.inscription(utilisateur);
    }

    @PostMapping(path = "activation")
    public  void activation(@RequestBody Map<String, String> activation){
        log.info("activation");
        this.utilisateurService.activation(activation);
    }

    @PostMapping(path = "connexion")
    public  Map<String, String> connexion(@RequestBody AuthentificationDTO authentificationDTO){

        final Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authentificationDTO.username(),
                        authentificationDTO.password()
                ));
        if(authentication.isAuthenticated()){
            return jwtServices.genrate(authentificationDTO.username());
        }
        log.info("Results {}", authentication.isAuthenticated());
        return null;
    }
}
