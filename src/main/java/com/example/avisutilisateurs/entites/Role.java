package com.example.avisutilisateurs.entites;

import com.example.avisutilisateurs.service.TypeDeRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;
    @Enumerated(EnumType.STRING)
    private TypeDeRole libelle;

}
