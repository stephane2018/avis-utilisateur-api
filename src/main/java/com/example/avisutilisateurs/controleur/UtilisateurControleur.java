package com.example.avisutilisateurs.controleur;

import com.example.avisutilisateurs.entites.Utilisateur;
import com.example.avisutilisateurs.securite.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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
    private  UtilisateurService utilisateurService;
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
}
