package com.example.avisutilisateurs.service;

import com.example.avisutilisateurs.entites.Validation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    JavaMailSender javaMailSender;
    public void envoyer(Validation validation){
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        mainMessage.setFrom("no-repley@bsd.tech");
        mainMessage.setTo(validation.getUtilisateur().getEmail());
        mainMessage.setSubject("Votre code d'activation");

        String messageTosend = String.format("Bonjour %s, <br/> voici le code d'activiationn %s a bientot",
                validation.getUtilisateur().getNom(), validation.getCode());
        mainMessage.setText(messageTosend);
        javaMailSender.send(mainMessage);
    }
}
