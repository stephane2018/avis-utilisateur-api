package com.example.avisutilisateurs.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="avis")
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    private  String status ;

}
